plugins {
    kotlin("jvm")
}

group = rootProject.group
version = rootProject.version

repositories {
    mavenCentral()
}

dependencies {
    val datetimeVersion: String by project
    implementation(kotlin("stdlib"))
    implementation(project(":common"))
    implementation(project(":stubs"))

    api("org.jetbrains.kotlinx:kotlinx-datetime:$datetimeVersion")
}