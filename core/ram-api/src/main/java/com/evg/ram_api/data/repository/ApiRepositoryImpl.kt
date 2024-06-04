package com.evg.ram_api.data.repository

import com.evg.ram_api.domain.KtorClient
import com.evg.ram_api.domain.Response
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
    override suspend fun getAllCharactersByPage(page: Int): Response<PageResponse<CharactersResponse>> {
        return ktor.executeApiSafe {
            ktor.client.get("/api/character?page=$page").body<PageResponse<CharactersResponse>>()
        }
    }
}