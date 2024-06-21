package com.evg.ram_api.data.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.evg.database.domain.repository.DatabaseRepository
import com.evg.ram_api.domain.KtorClient
import com.evg.ram_api.domain.Response
import com.evg.ram_api.domain.mapper.toCharacterDBO
import com.evg.ram_api.domain.mapper.toEpisodeDBO
import com.evg.ram_api.domain.mapper.toLocationDBO
import com.evg.ram_api.domain.models.CharacterFilterDTO
import com.evg.ram_api.domain.models.CharacterResponse
import com.evg.ram_api.domain.models.EpisodeFilterDTO
import com.evg.ram_api.domain.models.EpisodeResponse
import com.evg.ram_api.domain.models.LocationFilterDTO
import com.evg.ram_api.domain.models.LocationResponse
import com.evg.ram_api.domain.models.PageResponse
import com.evg.ram_api.domain.repository.ApiRepository
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.URLBuilder
import io.ktor.http.path
import io.ktor.http.takeFrom
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(
    private val context: Context,
    private val ktor: KtorClient,
    private val databaseRepository: DatabaseRepository,
): ApiRepository {
    //Characters
    override suspend fun getAllCharactersByPage(page: Int, filter: CharacterFilterDTO): Response<PageResponse<CharacterResponse>> {
        val url = URLBuilder().takeFrom(ktor.mainURL).apply {
            path("character")
            parameters.append("page", page.toString())
            filter.name?.let { parameters.append("name", it) }
            filter.status?.let { parameters.append("status", it.name.lowercase()) }
            filter.species?.let { parameters.append("species", it) }
            filter.type?.let { parameters.append("type", it) }
            filter.gender?.let { parameters.append("gender", it.name.lowercase()) }
        }.buildString()

        val response = ktor.executeApiSafe {
            ktor.client.get(url).body<PageResponse<CharacterResponse>>()
        }

        if (response is Response.Success) {
            databaseRepository.insertCharacters(
                characters = response.data.results.map { it.toCharacterDBO() }
            )
        }

        return response
    }

    override suspend fun getCharacterById(id: Int): Response<CharacterResponse> {
        val url = "${ktor.mainURL}/character/$id"
        val response = ktor.executeApiSafe {
            ktor.client.get(url).body<CharacterResponse>()
        }

        if (response is Response.Success) {
            databaseRepository.insertCharacter(
                character = response.data.toCharacterDBO()
            )
        }

        return response
    }

    override suspend fun getCharactersList(listIds: List<Int>): Response<List<CharacterResponse>> {
        if (listIds.isEmpty()) {
            return Response.Success(emptyList())
        }

        val idParams = listIds.joinToString(",")
        val url = "${ktor.mainURL}/character/$idParams"

        val response = ktor.executeApiSafe {
            if (listIds.size == 1) {
                val singleCharacter = ktor.client.get(url).body<CharacterResponse>()
                listOf(singleCharacter)
            } else {
                ktor.client.get(url).body<List<CharacterResponse>>()
            }
        }

        if (response is Response.Success) {
            databaseRepository.insertCharacters(
                characters = response.data.map { it.toCharacterDBO() }
            )
        }

        return response
    }

    //Episodes
    override suspend fun getAllEpisodesByPage(
        page: Int,
        filter: EpisodeFilterDTO
    ): Response<PageResponse<EpisodeResponse>> {
        val url = URLBuilder().takeFrom(ktor.mainURL).apply {
            path("episode")
            parameters.append("page", page.toString())
            filter.name?.let { parameters.append("name", it) }
        }.buildString()

        val response = ktor.executeApiSafe {
            ktor.client.get(url).body<PageResponse<EpisodeResponse>>()
        }
        if (response is Response.Success) {
            databaseRepository.insertEpisodes(
                episodes = response.data.results.map { it.toEpisodeDBO() }
            )
        }
        return response
    }

    override suspend fun getEpisodeById(id: Int): Response<EpisodeResponse> {
        val url = "${ktor.mainURL}/episode/$id"
        val response = ktor.executeApiSafe {
            ktor.client.get(url).body<EpisodeResponse>()
        }

        if (response is Response.Success) {
            databaseRepository.insertEpisode(
                episode = response.data.toEpisodeDBO()
            )
        }

        return response
    }

    override suspend fun getEpisodesList(listIds: List<Int>): Response<List<EpisodeResponse>> {
        if (listIds.isEmpty()) {
            return Response.Success(emptyList())
        }

        val idParams = listIds.joinToString(",")
        val url = "${ktor.mainURL}/episode/$idParams"

        val response = ktor.executeApiSafe {
            if (listIds.size == 1) {
                val singleEpisode = ktor.client.get(url).body<EpisodeResponse>()
                listOf(singleEpisode)
            } else {
                ktor.client.get(url).body<List<EpisodeResponse>>()
            }
        }

        if (response is Response.Success) {
            databaseRepository.insertEpisodes(
                episodes = response.data.map { it.toEpisodeDBO() }
            )
        }

        return response
    }

    //Characters
    override suspend fun getAllLocationsByPage(
        page: Int,
        filter: LocationFilterDTO
    ): Response<PageResponse<LocationResponse>> {
        val url = URLBuilder().takeFrom(ktor.mainURL).apply {
            path("location")
            parameters.append("page", page.toString())
            filter.name?.let { parameters.append("name", it) }
            filter.type?.let { parameters.append("type", it) }
            filter.dimension?.let { parameters.append("dimension", it) }
        }.buildString()

        val response = ktor.executeApiSafe {
            ktor.client.get(url).body<PageResponse<LocationResponse>>()
        }

        if (response is Response.Success) {
            databaseRepository.insertLocations(
                locations = response.data.results.map { it.toLocationDBO() }
            )
        }

        return response
    }

    override suspend fun getLocationById(id: Int): Response<LocationResponse> {
        val url = "${ktor.mainURL}/location/$id"
        val response = ktor.executeApiSafe {
            ktor.client.get(url).body<LocationResponse>()
        }

        if (response is Response.Success) {
            databaseRepository.insertLocation(
                location = response.data.toLocationDBO()
            )
        }

        return response
    }

    override suspend fun getLocationsList(listIds: List<Int>): Response<List<LocationResponse>> {
        if (listIds.isEmpty()) {
            return Response.Success(emptyList())
        }

        val idParams = listIds.joinToString(",")
        val url = "${ktor.mainURL}/location/$idParams"

        val response = ktor.executeApiSafe {
            if (listIds.size == 1) {
                val singleLocation = ktor.client.get(url).body<LocationResponse>()
                listOf(singleLocation)
            } else {
                ktor.client.get(url).body<List<LocationResponse>>()
            }
        }

        if (response is Response.Success) {
            databaseRepository.insertLocations(
                locations = response.data.map { it.toLocationDBO() }
            )
        }

        return response
    }


    override fun isInternetAvailable(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            else -> false
        }
    }

}
