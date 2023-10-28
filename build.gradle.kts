plugins {
    kotlin("jvm") version "1.9.0"
    application
    id("com.google.cloud.tools.jib") version "3.4.0"

}
val ktor_version = "2.3.5"
val kotlin_version = "1.9.0"
val logback_version = "1.3.11"
group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version")
    implementation("io.ktor:ktor-server-core:$ktor_version")
    implementation("io.ktor:ktor-server-cio:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version")
    implementation("io.ktor:ktor-server-core:$ktor_version")
    implementation("io.ktor:ktor-server-cio:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")

    testImplementation("io.ktor:ktor-server-test-host:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

application {
    mainClass.set("MainKt")
}

val artifactory_user: String by project
val artifactory_password: String by project


val jvmTarget: String? by project

//Allow manually setting jib.from.image as workaround for errors when pushing temurin based images to Jfrog.
val jibFromImage: String? by project
val jibFromImageSource: String? by project
val jibUser: String? by project

jib {
    val jibImage = "java17-debian11:nonroot"
    val jibSource = "https://gcr.io/distroless/"
    val jibPlatform = "amd64"
    from {
        image = "$jibSource$jibImage"
    }
    to {
        image = "${rootProject.name}:${project.version}"
        tags = setOf("latest")
    }
}