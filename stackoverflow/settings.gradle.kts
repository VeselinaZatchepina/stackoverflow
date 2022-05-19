rootProject.name = "stackoverflow"

pluginManagement {
    plugins {
        val kotlinVersion: String by settings
        val openApiVersion: String by settings

        kotlin("jvm") version kotlinVersion
        kotlin("plugin.serialization") version kotlinVersion
        id("org.openapi.generator") version openApiVersion
    }
}


include("common")
include("transport-main-openapi")
include("transport-mapping-openapi")
