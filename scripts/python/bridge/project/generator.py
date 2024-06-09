class BridgeBuildGradleSettings:
    pass

def generate_build_gradle(settings:BridgeBuildGradleSettings):
    return """
        plugins {
            id 'fabric-loom' version '1.6-SNAPSHOT'
            id 'java'
        }

        var minecraft_version = "1.16"
        var fabric_api_version = "0.42.0+1.16"
        var fabric_loader_version = "0.15.11"

        final JavaVersion java_version = JavaVersion.toVersion(project.java_version)
        version = project.mod_version
        group = project.maven_group
        base {
            archivesName = "fabric-bridge-1-16"
        }

        repositories {
            mavenCentral()
            mavenLocal()
        }

        dependencies {
            minecraft "com.mojang:minecraft:${minecraft_version}"
            mappings loom.officialMojangMappings()
            compileOnly "net.fabricmc:fabric-loader:${fabric_loader_version}"
            compileOnly "net.fabricmc.fabric-api:fabric-api:${fabric_api_version}"

            implementation "jakarta.annotation:jakarta.annotation-api:${project.jakarta_version}"

            compileOnly "org.projectlombok:lombok:${project.lombok_version}"
            annotationProcessor "org.projectlombok:lombok:${project.lombok_version}"
            testCompileOnly "org.projectlombok:lombok:${project.lombok_version}"
            testAnnotationProcessor "org.projectlombok:lombok:${project.lombok_version}"

            implementation project(":celib-api")
        }

        tasks.withType(JavaCompile).configureEach {
            it.options.release = java_version.ordinal() + 1
        }

        java {
            withSourcesJar()
            withJavadocJar()
            sourceCompatibility = java_version
            targetCompatibility = java_version
        }"""
