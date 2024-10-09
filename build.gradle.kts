plugins {
    id("java")
    id("io.freefair.lombok") version "8.10.2"
}

group = "cz.crowire"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Přidání závislosti na TestNG
    testImplementation("org.testng:testng:7.9.0")
    implementation("io.rest-assured:rest-assured:5.5.0")
}

tasks.test {
    // Nastavení použití TestNG jako testovacího frameworku
    useTestNG()
}