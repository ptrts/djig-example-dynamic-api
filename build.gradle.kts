plugins {
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("java-library")
    id("maven-publish")
}

group = "we.git-implementations"
version = "001"

java.sourceCompatibility = JavaVersion.VERSION_17

configurations {
    compileOnly {
        extendsFrom(configurations["annotationProcessor"])
    }
}

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {

    // Lombok
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    api("org.springframework:spring-core")
    api("org.springframework:spring-beans")
    api("org.springframework:spring-context")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.boot:spring-boot-dependencies:2.6.4")
    }
}

publishing {
    publications {
        create<MavenPublication>("jar") {
            // Плагин java (на базе которого сделан плагин java-library, который мы здесь используем) уже подготовил нам комплект для публикации
            // Такие заготовленные комплекты для публикации - это называется компоненты
            from(components["java"])

            versionMapping {
                // Если у некой зависимости, в выбранном ее варианте, есть вот такой атрибут
                //      org.gradle.usage=java-api
                // то сделать ей версию как в
                usage("java-api") {
                    fromResolutionResult()
                }
                usage("java-runtime") {
                    fromResolutionResult()
                }
            }
        }
    }
    repositories {
        maven {
            name = "s3MavenRepo"
            url = uri("s3://maven.taruts.net")
            authentication {
                // AwsImAuthentication means that the credentials are in an AWS profile on the computer
                // Only the author of this project has those credentials and can upload dynamic-api artifacts
                register("aws", AwsImAuthentication::class)
            }
        }
    }
}
