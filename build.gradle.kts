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

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.61"
    java
    maven
}

group = "dev.castive"
version = "4.1"
val moduleName by extra("dev.castive.log2")
val javaHome: String = System.getProperty("java.home")

repositories {
    maven(url = "https://jitpack.io")
    mavenCentral()
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    val slfVersion = "1.7.29"
    implementation("org.slf4j:slf4j-api:$slfVersion")


    testImplementation("org.slf4j:slf4j-simple:$slfVersion")

    val junitVersion = "5.5.2"
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-params:$junitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
}
configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
tasks {
    withType<Wrapper> {
        gradleVersion = "6.0.1"
        distributionType = Wrapper.DistributionType.ALL
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
}