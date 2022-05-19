plugins {
    kotlin("jvm") apply false
}

group = "org.stackoverflow"
version = "0.0.1"

subprojects {
    group = rootProject.group
    version = rootProject.version

    repositories {
        mavenCentral()
    }
}