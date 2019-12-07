# LOG2

Log2 is a library for simplifying logging. It uses Slf4j internally and therefore will not interfere with any custom logging solutions.

It is written in Kotlin and therefore should be fully interoperable with Java. 
If you are using ONLY Java, I'd recommend using Lombok's `@Slf4j` annotation instead.

Log2 is designed at the Java 11 language level and therefore may or may not work in lower versions of Java. YMMV.

### Setup

Gradle:

1. In your root build.gradle
```groovy
repositories {
    maven { url 'https://jitpack.io' }
}
```
```kotlin
repositories {
    maven(url = "https://jitpack.io")
}
```
2. In your project build.gradle
```groovy
dependencies {
    implementation 'com.github.djcass44:log2:4.1'
}
```
```kotlin
dependencies {
    implementation("com.github.djcass44:log2:4.1")
}
```
Maven:

1. Add the JitPack repository
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```
2. Add the dependency
```xml
<dependency>
    <groupId>com.github.djcass44</groupId>
    <artifactId>log2</artifactId>
    <version>4.1</version>
</dependency>
```

### Usage

```kotlin
Log.e(javaClass, "An error occurred!")
// Will print 
// 1970-01-01 00:00:00 000 | [main] |-ERROR in org.example.test.TestLog - An error occurred!
// kotlin extensions
"An error has occurred!".loge(javaClass)
try {
    something.throwsErr()
}
catch (e: Exception) {
    // include a throwable to print to console
    "Caught exception in somemethod".logf(javaClass, e)
}
```

### License

Log2 is released under the [Apache 2.0 license](LICENSE)
```
Copyright 2019 Django Cass

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

### Contributing

I will happily accept contributions, just fork and open a pull request!
