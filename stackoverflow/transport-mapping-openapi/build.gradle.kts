plugins {
    kotlin("jvm")
}

group = rootProject.group
version = rootProject.version

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))

    implementation(project(":common"))
    implementation(project(":transport-main-openapi"))

    testImplementation(kotlin("test"))
}