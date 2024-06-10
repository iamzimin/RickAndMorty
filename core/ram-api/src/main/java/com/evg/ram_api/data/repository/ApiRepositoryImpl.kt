package com.evg.ram_api.data.repository

import com.evg.database.domain.repository.DatabaseRepository
import com.evg.ram_api.domain.KtorClient
import com.evg.ram_api.domain.Response
import com.evg.ram_api.domain.mapper.toCharacterDBO
import com.evg.ram_api.domain.models.CharacterFilterDTO
import com.evg.ram_api.domain.models.CharactersResponse
import com.evg.ram_api.domain.models.PageResponse
import com.evg.ram_api.domain.repository.ApiRepository
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.URLBuilder
import io.ktor.http.URLProtocol
import io.ktor.http.encodedPath
import io.ktor.http.path
import io.ktor.http.takeFrom
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.Path
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(
    private val ktor: KtorClient,
    private val databaseRepository: DatabaseRepository,
): ApiRepository {
    override suspend fun getAllCharactersByPage(page: Int, filter: CharacterFilterDTO): Response<PageResponse<CharactersResponse>> {
        val url = buildUrlWithParams(
            baseUrl = ktor.mainURL,
            path = "character",
            page = page,
            filter = filter
        )

        val response = ktor.executeApiSafe {
            ktor.client.get(url).body<PageResponse<CharactersResponse>>()
        }
        if (response is Response.Success) {
            databaseRepository.insertCharacters(
                characters = response.data.results.map { it.toCharacterDBO() }
            )
        }
        return response
    }

}

private fun buildUrlWithParams(baseUrl: URLBuilder, path: String, page: Int, filter: CharacterFilterDTO): String {
    val urlBuilder = URLBuilder().takeFrom(baseUrl)

    urlBuilder.apply {
        path(path)
        parameters.append("page", page.toString())
        filter.name?.let { parameters.append("name", it) }
        filter.status?.let { parameters.append("status", it.name.lowercase()) }
        filter.species?.let { parameters.append("species", it) }
        filter.type?.let { parameters.append("type", it) }
        filter.gender?.let { parameters.append("gender", it.name.lowercase()) }
    }

    return urlBuilder.buildString()
}
