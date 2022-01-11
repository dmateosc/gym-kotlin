pluginManagement {
    repositories {
        maven { url = uri("https://repo.spring.io/release") }
        gradlePluginPortal()
    }
}
rootProject.name = "gym-kotlin"

include("gym")
project(":gym").projectDir = File("src/gym")
