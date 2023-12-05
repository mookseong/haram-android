pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        mavenLocal()
        maven("https://naver.jfrog.io/artifactory/maven/")
    }
}
rootProject.name = "biblemon"
include (":app")
include (":domain")
include (":shared")
include(":repository")
include(":features:home")
include(":features:core-ui")
include(":features:book")
include(":features:navigator")
include(":features:mileage")
