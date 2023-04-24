package io.kotest.assertions.ktor

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.server.application.Application

class Configure {

   internal var configureClient: HttpClientConfig<out HttpClientEngineConfig>.() -> Unit = {}
   internal var configureServer: Application.() -> Unit = {}

   fun client(block: (HttpClientConfig<out HttpClientEngineConfig>.() -> Unit)) {
      configureClient = block
   }

   fun server(block: Application.() -> Unit) {
      configureServer = block
   }

   suspend fun testApplication(test: suspend ApplicationTest.() -> Unit) {
      val configure = this
      io.ktor.server.testing.testApplication {
         application {
            configure.configureServer(this)
         }
         val client = createClient {
            configure.configureClient(this)
         }
         ApplicationTest(client).test()
      }
   }
}

fun configure(block: Configure.() -> Unit): Configure {
   val config = Configure()
   config.block()
   return config
}

class ApplicationTest(val client: HttpClient)
