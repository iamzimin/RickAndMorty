package com.evg.ram_api.data.repository

import com.evg.ram_api.domain.KtorClient
import com.evg.ram_api.domain.models.CharactersResponse
import com.evg.ram_api.domain.models.PageResponse
import com.evg.ram_api.domain.repository.ApiRepository
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ApiRepositoryImpl(
    private val ktor: KtorClient
): ApiRepository {
    override fun getAllCharacters(): Flow<List<CharactersResponse>> = flow {
        val response: HttpResponse = ktor.client.get("/api/character")
        val charactersResponse = response.body<PageResponse<CharactersResponse>>()
        emit(charactersResponse.results)
    }
}