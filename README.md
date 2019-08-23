# LOG2

Log2 is a simple library for showing pretty log statements.

It is written in Kotlin and therefore should be fully interoperable with Java

Log2 is a rewrite of a project by the same name ([Log2](https://gitlab.com/django-sandbox/log2))
### Download

Gradle:

1. In your root build.gradle
```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
2. In your project build.gradle
```gradle
dependencies {
    implementation 'com.github.djcass44:log2:3.0'
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
    <version>3.0</version>
</dependency>
```

### Usage

```kotlin
Log.e(javaClass, "An error occurred!")
// Will print 
// 1970-01-01 00:00:00 000 | [main] |-ERROR in org.example.test.TestLog - An error occurred!
```

**Additional options**

```Log.USE_SHORT_COLOURS```(default: true): Only colour the part of the log which shows severity

### License

Log2 is releasesed under the [Apache 2.0 license](LICENSE)
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
