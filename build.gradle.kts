plugins {
	java
	id("org.springframework.boot") version "3.3.4"
	id("io.spring.dependency-management") version "1.1.6"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
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
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-mail")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-web")
//	implementation("io.springfox:springfox-boot-starter:3.0.0")
	implementation("javax.servlet:javax.servlet-api:4.0.1")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0")

	compileOnly("org.projectlombok:lombok")
	implementation("io.jsonwebtoken:jjwt-api:0.12.5")
	implementation("io.jsonwebtoken:jjwt-impl:0.12.5")
	implementation("io.jsonwebtoken:jjwt-jackson:0.12.5")
	implementation("org.springframework.boot:spring-boot-starter-mail")





//	developmentOnly("org.springframework.boot:spring-boot-docker-compose")
	runtimeOnly("com.mysql:mysql-connector-j")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
