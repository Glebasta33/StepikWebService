plugins {
    kotlin("jvm")
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
    // https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-core
    implementation("org.apache.logging.log4j:log4j-core:2.17.1")
// https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-api
    implementation("org.apache.logging.log4j:log4j-api:2.17.1")
// https://mvnrepository.com/artifact/org.mockito/mockito-core
    testImplementation("org.mockito:mockito-core:5.11.0")

}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}