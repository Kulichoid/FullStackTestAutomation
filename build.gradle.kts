plugins {
    id("java")
    id("io.freefair.lombok") version "8.10.2"
    id("io.qameta.allure") version "2.12.0"
}

group = "cz.crowire"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.rest-assured:rest-assured:5.5.0")
    implementation("org.aspectj:aspectjweaver:1.9.22")// Aktualizovaná verze Allure TestNG integrace
    implementation("com.fasterxml.jackson.core:jackson-databind:2.14.1")
    implementation("ch.qos.logback:logback-classic:1.4.12")
    testImplementation("org.testng:testng:7.9.0")
    testImplementation("org.seleniumhq.selenium:selenium-java:4.25.0")
    testImplementation("io.qameta.allure:allure-testng:2.29.0")
}

tasks.test {
    useTestNG()
}