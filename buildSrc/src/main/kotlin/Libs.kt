object Libs {

   const val kotlinVersion = "1.4.31"
   const val org = "io.kotest.extensions"
   const val dokkaVersion = "0.10.1"

   object Kotest {
      private const val version = "4.4.3"
      const val assertionsShared = "io.kotest:kotest-assertions-shared:$version"
      const val api = "io.kotest:kotest-framework-api:$version"
      const val junit5 = "io.kotest:kotest-runner-junit5-jvm:$version"
   }

   object Ktor {
      private const val version = "1.5.1"
      const val serverCore = "io.ktor:ktor-server-core:$version"
      const val serverTestHost = "io.ktor:ktor-server-test-host:$version"
      const val clientCore = "io.ktor:ktor-client-core:$version"
   }
}
