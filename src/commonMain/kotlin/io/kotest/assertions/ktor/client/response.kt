package io.kotest.assertions.ktor.client

import io.kotest.matchers.Matcher
import io.kotest.matchers.MatcherResult
import io.kotest.matchers.should
import io.kotest.matchers.shouldNot
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpProtocolVersion
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType

infix fun HttpResponse.shouldHaveETag(etag: String) = this should haveETag(etag)
infix fun HttpResponse.shouldNotHaveETag(etag: String) = this shouldNot haveETag(etag)
fun haveETag(expected: String) = object : Matcher<HttpResponse> {
   override fun test(value: HttpResponse): MatcherResult {
      val actual = value.headers[HttpHeaders.ETag]
      return MatcherResult(
         actual == expected,
         "Response should have ETag $expected but had ${actual}. Response body: ${value.content}",
         "Response should not have ETag $expected. Response body: ${value.content}"
      )
   }
}

infix fun HttpResponse.shouldHaveCacheControl(cacheControl: String) = this should haveCacheControl(cacheControl)
infix fun HttpResponse.shouldNotCacheControl(cacheControl: String) = this shouldNot haveCacheControl(cacheControl)
fun haveCacheControl(expected: String) = object : Matcher<HttpResponse> {
   override fun test(value: HttpResponse): MatcherResult {
      val actual = value.headers[HttpHeaders.CacheControl]
      return MatcherResult(
         actual == expected,
         "Response should have Cache-Control: $expected but had: ${actual}. Response body: ${value.content}",
         "Response should not have Cache-Control $expected. Response body: ${value.content}"
      )
   }
}

infix fun HttpResponse.shouldHaveContentEncoding(encoding: String) = this should haveContentEncoding(encoding)
infix fun HttpResponse.shouldNotHaveContentEncoding(encoding: String) = this shouldNot haveContentEncoding(encoding)
fun haveContentEncoding(expected: String) = object : Matcher<HttpResponse> {
   override fun test(value: HttpResponse): MatcherResult {
      val actual = value.headers[HttpHeaders.ContentEncoding]
      return MatcherResult(
         actual == expected,
         "Response should have Content-Encoding: $expected but had: ${actual}. Response body: ${value.content}",
         "Response should not have Content-Encoding $expected. Response body: ${value.content}"
      )
   }
}

infix fun HttpResponse.shouldHaveStatus(httpStatusCode: HttpStatusCode) = shouldHaveStatus(httpStatusCode.value)
infix fun HttpResponse.shouldHaveStatus(code: Int) = this should haveStatus(code)
infix fun HttpResponse.shouldNotHaveStatus(httpStatusCode: HttpStatusCode) = shouldNotHaveStatus(httpStatusCode.value)
infix fun HttpResponse.shouldNotHaveStatus(code: Int) = this shouldNot haveStatus(code)
fun haveStatus(expected: Int) = object : Matcher<HttpResponse> {
   override fun test(value: HttpResponse): MatcherResult {
      return MatcherResult(
         value.status.value == expected,
         "Response should have status $expected but had status ${value.status.value}. Response body: ${value.content}",
         "Response should not have status $expected. Response body: ${value.content}"
      )
   }
}

infix fun HttpResponse.shouldHaveVersion(version: HttpProtocolVersion) = this should haveVersion(version)
infix fun HttpResponse.shouldNotHaveVersion(version: HttpProtocolVersion) = this shouldNot haveVersion(version)
fun haveVersion(version: HttpProtocolVersion) = object : Matcher<HttpResponse> {
   override fun test(value: HttpResponse): MatcherResult {
      return MatcherResult(
         value.version == version,
         "Response should have version $version but had version ${value.version}",
         "Response should not have version $version"
      )
   }
}

fun HttpResponse.shouldHaveHeader(name: String, value: String) = this should haveHeader(name, value)
fun HttpResponse.shouldNotHaveHeader(name: String, value: String) = this shouldNot haveHeader(name, value)
fun haveHeader(headerName: String, headerValue: String) = object : Matcher<HttpResponse> {
   override fun test(value: HttpResponse): MatcherResult {
      return MatcherResult(
         value.headers[headerName] == headerValue,
         "Response should have header $headerName=$value but $headerName=${value.headers[headerName]}",
         "Response should not have header $headerName=$value"
      )
   }
}

fun HttpResponse.shouldHaveContentType(contentType: ContentType) = this should haveContentType(contentType)
fun HttpResponse.shouldNotHaveContentType(contentType: ContentType) = this shouldNot haveContentType(contentType)
fun haveContentType(contentType: ContentType) = object : Matcher<HttpResponse> {
   override fun test(value: HttpResponse): MatcherResult {
      return MatcherResult(
         value.contentType() == contentType,
         "Response should have ContentType $contentType= but was ${value.contentType()}",
         "Response should not have ContentType $contentType"
      )
   }
}
