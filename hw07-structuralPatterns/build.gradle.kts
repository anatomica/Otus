plugins {
    id ("java")
}

dependencies {
    implementation("org.apache.commons:commons-lang3")
    implementation("org.apache.commons:commons-collections4")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    annotationProcessor("org.projectlombok:lombok")
    compileOnly("org.projectlombok:lombok")
}