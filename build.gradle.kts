
plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(ktorLibs.plugins.ktor)
    alias(libs.plugins.kotlin.serialization)
}

group = "chaitnya.dev"
version = "1.0.0-SNAPSHOT"

application {
    mainClass = "io.ktor.server.netty.EngineMain"
}

kotlin {
    jvmToolchain(21)
}
dependencies {
    implementation(ktorLibs.serialization.kotlinx.json)
    implementation(ktorLibs.server.contentNegotiation)
    implementation(ktorLibs.server.core)
    implementation(ktorLibs.server.netty)
    implementation(libs.exposed.core)
    implementation(libs.exposed.r2dbc)
    implementation(libs.h2database.h2)
    implementation(libs.h2database.r2dbc)
    implementation(libs.logback.classic)
    implementation(libs.ktor.server.request.validation)
    implementation(libs.ktor.server.status.pages)
    implementation(libs.postgresql)

    testImplementation(kotlin("test"))
    testImplementation(ktorLibs.server.testHost)
}
