import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.kotlin.jvm)
    `maven-publish`
}

group = "io.github.kingsword09"
version = "0.1.0"

dependencies {
    implementation(libs.google.guava)
    implementation(libs.android.tools.sdk)
    implementation(libs.android.tools)
    implementation(libs.com.squareup)
    implementation(libs.org.ogce)

    testImplementation(kotlin("test-junit"))
}

tasks.test {
    useJUnit()
}

tasks.withType<KotlinCompile>() {
    compilerOptions.jvmTarget.set(JvmTarget.JVM_17)
}

kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of("17"))
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["kotlin"])
        }
    }
}
