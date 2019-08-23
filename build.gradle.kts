/*
 *    Copyright 2019 Django Cass
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *
 */

import org.ajoberstar.grgit.Grgit
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.sonarqube.gradle.SonarQubeTask

plugins {
    kotlin("jvm") version "1.3.31"
    java
    maven
    jacoco
    id("org.sonarqube") version "2.7.1"
    id("org.ajoberstar.grgit") version "1.7.2"
}

group = "dev.castive"
version = "3.1"
val moduleName by extra("dev.castive.log2")
val javaHome: String = System.getProperty("java.home")

repositories {
    maven(url = "https://jitpack.io")
    mavenCentral()
    jcenter()
}

dependencies {
    api("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.3.31:modular")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.2.0")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.2.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.2.0")
}
configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
tasks {
    withType<Wrapper> {
        gradleVersion = "5.2"
        distributionType = Wrapper.DistributionType.BIN
    }
    withType<KotlinCompile>().all {
        kotlinOptions.jvmTarget = "11"
    }
    withType<JavaCompile>().all {
        inputs.property("moduleName", moduleName)
        doFirst {
            options.compilerArgs = listOf(
                "--module-path", classpath.asPath,
                "--patch-module", "$moduleName=${sourceSets["main"].output.asPath}"
            )
            classpath = files()
        }
    }

    withType<Test> {
        useJUnitPlatform()
    }
    withType<JacocoReport> {
        reports {
            xml.isEnabled = true
        }
    }
    withType<SonarQubeTask> {
        dependsOn("test", "jacocoTestReport")
    }
}
jacoco {
    toolVersion = "0.8.4"
}
val codeCoverageReport by tasks.creating(JacocoReport::class) { dependsOn("test") }

sonarqube {
    val git = runCatching { Grgit.open(project.rootDir) }.getOrNull()
    // Don't run an analysis if we can't get git context
    val name = if(git == null) null else runCatching { git.branch.current.name }.getOrNull()
    val target = when(name) {
        null -> null
        "develop" -> "master"
        else -> "develop"
    }
    val branch = if(name != null && target != null) (name to target) else null
    this.isSkipProject = branch == null
    properties {
        property("sonar.projectKey", "djcass44:fav2")
        property("sonar.projectName", "djcass44/fav2")
//		if(branch != null) {
//			property("sonar.branch.name", branch.first)
//			property("sonar.branch.target", branch.second)
//		}
        property("sonar.jacoco.xmlReportPaths", "$projectDir/build/test-results/test")
    }
}