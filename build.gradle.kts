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

   targets {

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
      watchosX86()
      watchosArm32()
      watchosArm64()

      macosArm64()
      macosX64()
      tvos()

      iosX64()
      iosArm64()
      iosArm32()
      iosSimulatorArm64()
   }

   sourceSets {

      val commonMain by getting {
         dependencies {
            implementation(libs.kotest.assertionsShared)
            implementation(libs.ktorClient.core)
         }
      }

      val jvmMain by getting {
         dependsOn(commonMain)
         dependencies {
            implementation(libs.ktorServer.core)
            implementation(libs.ktorServer.testHost)
         }
      }

      val jvmTest by getting {
         dependsOn(jvmMain)
         dependencies {
            implementation(libs.kotest.runnerJunit5)
         }
      }

      val desktopMain by creating {
         dependsOn(commonMain)
      }

      val watchosX86Main by getting {
         dependsOn(desktopMain)
      }

      val watchosX64Main by getting {
         dependsOn(desktopMain)
      }

      val watchosArm32Main by getting {
         dependsOn(desktopMain)
      }

      val watchosArm64Main by getting {
         dependsOn(desktopMain)
      }

      val macosArm64Main by getting {
         dependsOn(desktopMain)
      }

      val macosX64Main by getting {
         dependsOn(desktopMain)
      }

      val mingwX64Main by getting {
         dependsOn(desktopMain)
      }

      val linuxX64Main by getting {
         dependsOn(desktopMain)
      }

      val iosX64Main by getting {
         dependsOn(desktopMain)
      }

      val iosArm64Main by getting {
         dependsOn(desktopMain)
      }

      val iosArm32Main by getting {
         dependsOn(desktopMain)
      }

      val iosSimulatorArm64Main by getting {
         dependsOn(desktopMain)
      }

      val tvosMain by getting {
         dependsOn(desktopMain)
      }
   }
}

tasks.named<Test>("jvmTest") {
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
