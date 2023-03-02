package io.kotest.assertions.ktor.server

import io.kotest.assertions.ktor.client.shouldHaveStatus
import io.kotest.core.spec.style.FunSpec
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.testing.testApplication

class ServerFixtureTest : FunSpec() {

   init {

      testApplication {
         application {
            routing {
               get("/foo") { call.respond(HttpStatusCode.Created) }
            }
         }
         val resp = client.get("/foo")
         resp shouldHaveStatus HttpStatusCode.OK
      }
   }
}
