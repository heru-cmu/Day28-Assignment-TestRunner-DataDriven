plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Test framework
    testImplementation("org.testng:testng:7.11.0")
    testImplementation("org.assertj:assertj-core:3.27.7")

    // Selenium & WebDriver
    implementation("org.seleniumhq.selenium:selenium-java:4.35.0")
    implementation("org.seleniumhq.selenium:selenium-devtools-v139:4.35.0")
    implementation("io.github.bonigarcia:webdrivermanager:6.3.2")

    // Excel (Apache POI)
    implementation("org.apache.poi:poi:5.4.1")
    implementation("org.apache.poi:poi-ooxml:5.4.1")

    // Reporting
    implementation("com.aventstack:extentreports:5.1.2")

    // Logging
    implementation("org.apache.logging.log4j:log4j-core:2.25.3")
    implementation("org.apache.logging.log4j:log4j-api:2.25.3")
    implementation("org.apache.logging.log4j:log4j-slf4j2-impl:2.25.3") // binding SLF4J → Log4j
}

tasks.test {
    useTestNG {
        suites("src/test/resources/suites/smoke.xml")

        if (project.hasProperty("env")) {
            systemProperty("env", project.property("env") as String)
        }

        if (project.hasProperty("browser")) {
            systemProperty("browser", project.property("browser") as String)
        }
    }
}

sourceSets {
    test {
        resources {
            srcDir("src/test/resources")
        }
    }
}

tasks.processTestResources {
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}
