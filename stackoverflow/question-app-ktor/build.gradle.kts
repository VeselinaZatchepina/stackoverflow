import org.jetbrains.kotlin.util.suffixIfNot

plugins {
    kotlin("jvm")
    id("application")
}

val ktorVersion: String by project

// ex: Converts to "io.ktor:ktor-ktor-server-netty:2.0.1" with only ktor("netty")
fun DependencyHandler.ktor(module: String, prefix: String = "server-", version: String? = ktorVersion): Any =
    "io.ktor:ktor-${prefix.suffixIfNot("-")}$module:$version"

group = rootProject.group
version = rootProject.version


repositories {
    mavenCentral()
    maven { url = uri("https://jitpack.io") }
}

application {
    mainClass.set("io.ktor.server.netty.EngineMain")
}

dependencies {
    val datetimeVersion: String by project
    implementation(kotlin("stdlib"))
    implementation(project(":services"))
    implementation(project(":common"))
    implementation(project(":transport-mapping-openapi"))
    implementation(project(":transport-main-openapi"))

    api("org.jetbrains.kotlinx:kotlinx-datetime:$datetimeVersion")

    val logbackVersion: String by project
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation(ktor("core")) // "io.ktor:ktor-server-core:$ktorVersion"
    implementation(ktor("netty")) // "io.ktor:ktor-ktor-server-netty:$ktorVersion"

    // jackson
    implementation(ktor("jackson", "serialization")) // io.ktor:ktor-serialization-jackson
    implementation(ktor("content-negotiation")) // io.ktor:ktor-server-content-negotiation

    implementation(ktor("locations"))
    implementation(ktor("caching-headers"))
    implementation(ktor("call-logging"))
    implementation(ktor("auto-head-response"))
    implementation(ktor("cors")) // "io.ktor:ktor-cors:$ktorVersion"
    implementation(ktor("default-headers")) // "io.ktor:ktor-cors:$ktorVersion"
    implementation(ktor("cors")) // "io.ktor:ktor-cors:$ktorVersion"
    implementation(ktor("auto-head-response"))
    implementation(ktor("websockets"))

    implementation(ktor("websockets"))

    implementation("ch.qos.logback:logback-classic:$logbackVersion")

    testImplementation(kotlin("test-junit"))
    testImplementation(ktor("test-host"))
}
