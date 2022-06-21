plugins {
    kotlin("jvm")
}

group = rootProject.group
version = rootProject.version

repositories {
    mavenCentral()
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    val datetimeVersion: String by project
    implementation(kotlin("stdlib"))
    implementation(project(":common"))
    implementation(project(":stubs"))
    implementation(project(":question-app-biz"))

    api("org.jetbrains.kotlinx:kotlinx-datetime:$datetimeVersion")
}