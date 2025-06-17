plugins {
	application
	checkstyle
	jacoco
	id("org.springframework.boot") version "3.5.0"
	id("io.spring.dependency-management") version "1.1.7"
	id("io.freefair.lombok") version "8.13"
	id("org.sonarqube") version "6.2.0.5505"
	id("io.sentry.jvm.gradle") version "5.7.0"
}

group = "hexlet.code"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

sonar {
	properties {
		property("sonar.projectKey", "rogatin-av_java-project-99")
		property("sonar.organization", "rogatin-av")
		property("sonar.host.url", "https://sonarcloud.io")
	}
}


val sentryToken = System.getenv("SENTRY_AUTH_TOKEN") ?: "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"

sentry {
	includeSourceContext = true

	org = "hexlet-r4"
	projectName = "java-spring-boot"
	//authToken = System.getenv("SENTRY_AUTH_TOKEN")
	authToken = sentryToken
}

tasks.withType<JavaExec>().configureEach {
	systemProperty("SENTRY_AUTH_TOKEN", System.getProperty("SENTRY_AUTH_TOKEN"))
}

application {
	mainClass.set("hexlet.code.AppApplication")
}

tasks.jacocoTestReport {
	reports {
		xml.required.set(true)
	}
}


dependencies {
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.8")
	implementation("org.springframework.boot:spring-boot-configuration-processor")
	implementation("org.openapitools:jackson-databind-nullable:0.2.6")
	implementation("org.instancio:instancio-junit:5.0.2")
	implementation("net.javacrumbs.json-unit:json-unit-assertj:4.0.0")
	implementation("net.datafaker:datafaker:2.4.3")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
	testImplementation("org.springframework.security:spring-security-test")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.mapstruct:mapstruct:1.6.3")
	annotationProcessor("org.mapstruct:mapstruct-processor:1.6.3")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	runtimeOnly("com.h2database:h2")
	implementation("org.postgresql:postgresql")
	implementation("org.springframework.boot:spring-boot-starter")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	implementation("org.springframework.boot:spring-boot-starter-web")
	testImplementation(platform("org.junit:junit-bom:5.12.0"))
	testImplementation("org.junit.jupiter:junit-jupiter:5.12.0")
	implementation("org.springframework.boot:spring-boot-devtools")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
