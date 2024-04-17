plugins {
    kotlin("jvm") version "1.9.21"
}

group = "com.github.gltrusov"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    implementation("org.eclipse.jetty:jetty-server:9.3.0.M0")
    implementation("org.eclipse.jetty:jetty-webapp:9.3.0.M0")
    implementation("javax.servlet:javax.servlet-api:4.0.1")
    implementation("org.freemarker:freemarker:2.3.20")
    implementation("com.google.code.gson:gson:2.7")

    implementation("com.h2database:h2:1.3.148")


}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}