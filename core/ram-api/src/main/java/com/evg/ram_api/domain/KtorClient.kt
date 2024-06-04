package com.evg.ram_api.domain

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class KtorClient {
    val client = HttpClient(OkHttp) {
        defaultRequest { url("https://rickandmortyapi.com") }

        install(Logging) {
            logger = Logger.SIMPLE
        }

        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }

    inline fun <T> executeApiSafe(apiCall: () -> T): Response<T> {
        return try {
            Response.Success(data = apiCall())
        } catch (e: Exception) {
            Response.Failure(exception = e)
        }
    }
}