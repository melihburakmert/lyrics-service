plugins {
	kotlin("jvm") version "1.9.24"
	kotlin("plugin.spring") version "1.9.24"
	id("org.springframework.boot") version "3.4.1"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "mbm"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion.set(JavaLanguageVersion.of(21))
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	// BOMs
	implementation(platform("org.springframework.cloud:spring-cloud-dependencies:2024.0.0"))
	implementation(platform("com.google.cloud:spring-cloud-gcp-dependencies:5.9.0"))

	// Coroutines
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.1")

	// Spring Boot and Web
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")

	// Logging
	implementation("io.github.oshai:kotlin-logging-jvm:7.0.0")
	implementation("org.slf4j:slf4j-api")
	implementation("ch.qos.logback:logback-classic")

	// Google Cloud Pub/Sub
	implementation("com.google.cloud:spring-cloud-gcp-starter-pubsub")
	implementation("com.google.cloud:spring-cloud-gcp-pubsub-stream-binder")
	// Need to specify the version as newer version is not suported with the latest kotlin plugin
	implementation("org.springframework.cloud:spring-cloud-function-context:4.1.3")

	// Testing
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testImplementation("org.springframework.cloud:spring-cloud-stream-test-binder")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
	jvmToolchain {
		languageVersion.set(JavaLanguageVersion.of(21))
	}
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
