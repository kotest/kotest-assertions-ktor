# kotest-assertions-ktor

Kotest assertions for [Ktor](https://ktor.io/).

See [docs](https://kotest.io/docs/assertions/ktor-matchers.html).

Please create issues on the main kotest [board](https://github.com/kotest/kotest/issues).

[![Build Status](https://github.com/kotest/kotest-assertions-ktor/workflows/master/badge.svg)](https://github.com/kotest/kotest-assertions-ktor/actions)
[<img src="https://img.shields.io/maven-central/v/io.kotest.extensions/kotest-assertions-ktor.svg?label=latest%20release"/>](http://search.maven.org/#search|ga|1|kotest-assertions-ktor)
![GitHub](https://img.shields.io/github/license/kotest/kotest-assertions-ktor)
[![kotest @ kotlinlang.slack.com](https://img.shields.io/static/v1?label=kotlinlang&message=kotest&color=blue&logo=slack)](https://kotlinlang.slack.com/archives/CT0G9SD7Z)
[<img src="https://img.shields.io/nexus/s/io.kotest.extensions/kotest-assertions-ktor?label=latest%20snapshot&server=https%3A%2F%2Fs01.oss.sonatype.org"/>](https://s01.oss.sonatype.org/content/repositories/snapshots/io/kotest/extensions/kotest-assertions-ktor/)

**Dependency**
```kotlin
testImplementation("io.kotest.extensions:kotest-assertions-ktor:version")
```

## Changelog

### 2.0.0

* Updated Ktor to 2.2.3
* Added new native targets: macosArm64, iosSimulatorArm64, watchosX86, watchosX64,watchosArm32, watchosArm64

### 1.0.3

* Added matchers for Cache-Control, Content-Encoding and ETag

### 1.0.2

* Fixed error message when testing header values #2

### 1.0.1

* Added IR build for JS #1
* Updated to Ktor 1.5.3
