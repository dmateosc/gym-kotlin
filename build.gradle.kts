import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootBuildImage


plugins {
    id("org.springframework.boot") version "2.5.5"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.5.31"
    kotlin("plugin.spring") version "1.5.31"
    kotlin("plugin.jpa") version "1.4.32"
    application
}

allprojects {

    apply (plugin = "java")
    apply (plugin= "io.spring.dependency-management")
    apply (plugin = "org.springframework.boot")

group = "gym-kotlin"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_15

repositories {

    mavenCentral()
    maven {
        url = uri("https://maven.springframework.org/release")
    }
    maven {
        url = uri("https://maven.restlet.com")
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    implementation("org.hibernate:hibernate-core:5.4.9.Final")
    implementation("io.github.cdimascio:java-dotenv:5.1.3")
    implementation ("org.apache.tomcat:tomcat-dbcp:9.0.29")


    //security

    implementation("io.jsonwebtoken:jjwt:0.9.1")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    developmentOnly("org.springframework.boot:spring-boot-devtools")

    testImplementation("io.mockk:mockk:1.12.0")
    testImplementation("com.ninja-squad:springmockk:3.0.1")
    runtimeOnly("org.postgresql:postgresql")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.security:spring-security-config")
    implementation("org.springframework.security:spring-security-jwt:1.1.1.RELEASE")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb:2.6.1")
    implementation ("io.projectreactor:reactor-bus:2.0.8.RELEASE")
    implementation("io.projectreactor.spring:reactor-spring-messaging:2.0.7.RELEASE")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions:1.1.5")
    implementation("io.projectreactor:reactor-core:2.0.8.RELEASE")

}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "15"
    }
}




tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<BootBuildImage> {
    builder = "paketobuildpacks/builder:tiny"
    environment = mapOf("BP_NATIVE_IMAGE" to "true")
}

}
subprojects{
    group = "gym.${rootProject.name}"
    sourceSets {
        main{
            java {
                srcDirs("main")
            }
            resources{
                srcDirs("main/resources")
            }
        }
        test{
            java {
                srcDirs("test")
            }
            resources{
                srcDirs("test/resources")
            }
        }
    }

    dependencies{
        implementation("org.springframework.boot:spring-boot-starter-web")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
    }

    tasks.bootJar {enabled = false}

    tasks.jar{ enabled = true}
}

buildscript {
    repositories {
        mavenCentral()
    }
    dependencies{
        classpath("org.springframework.boot:spring-boot-gradle-plugin:2.5.5")
    }
}

sourceSets{
    main{
        java{
            srcDirs("apps/main")

        }
        resources {
            srcDirs("apps/main/resources")
        }
    }
    test{
        java{
            srcDirs("apps/test")

        }
        resources {
            srcDirs("apps/test/resources")
        }
    }


}

application{
    mainClass.set("apps.main.Gym")
}

dependencies{
   implementation(project(":gym"))
}

