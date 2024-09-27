buildscript {
   repositories {
      mavenCentral()
      maven {
         url = uri("https://oss.sonatype.org/content/repositories/snapshots/")
         mavenContent { snapshotsOnly() }
      }
      maven {
         url = uri("https://plugins.gradle.org/m2/")
      }
   }
}

plugins {
   java
   `java-library`
   signing
   `maven-publish`
   kotlin("multiplatform") version libs.versions.kotlin
}

repositories {
   mavenCentral()
   maven {
      url = uri("https://oss.sonatype.org/content/repositories/snapshots")
      mavenContent { snapshotsOnly() }
   }
   mavenLocal()
}

group = "io.kotest.extensions"
version = Ci.version

kotlin {
   jvm {
      compilations.all {
         kotlinOptions {
            jvmTarget = "1.8"
         }
      }
   }

   js(IR) {
      browser()
      nodejs()
   }

   linuxX64()

   mingwX64()

   watchosX64()
   watchosArm32()
   watchosArm64()

   macosArm64()
   macosX64()
   tvosX64()
   tvosArm64()
   tvosSimulatorArm64()

   iosX64()
   iosArm64()
   iosSimulatorArm64()

   sourceSets {
      commonMain {
         dependencies {
            implementation(libs.kotest.assertionsShared)
            implementation(libs.ktorClient.core)
         }
      }

      jvmMain {
         dependencies {
            implementation(libs.ktorServer.core)
            implementation(libs.ktorServer.testHost)
         }
      }

      jvmTest {
         dependencies {
            implementation(libs.kotest.runnerJunit5)
         }
      }
   }
}

tasks.named<Test>("jvmTest").configure {
   useJUnitPlatform()
   filter {
      isFailOnNoMatchingTests = false
   }
   testLogging {
      showExceptions = true
      showStandardStreams = true
      events = setOf(
         org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED,
         org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED
      )
      exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
   }
}

apply("./publish-mpp.gradle.kts")
