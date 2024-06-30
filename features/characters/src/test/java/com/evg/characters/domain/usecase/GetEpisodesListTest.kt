package com.evg.characters.domain.usecase

import com.evg.characters.domain.model.Episode
import com.evg.characters.domain.repository.CharactersRepository
import com.evg.characters.utils.MockFactory.createMockEpisode
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.mockito.Mockito.mock
import org.mockito.Mockito.reset
import org.mockito.Mockito.`when`
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

class GetEpisodesListTest {
    companion object {
        @JvmStatic
        fun existingIdsProvider(): List<List<Int>> {
            return listOf(
                listOf(1),
                listOf(6, 9, 12),
                listOf(19, 21, 45, 49, 55),
            )
        }
        @JvmStatic
        fun notExistingIdsProvider(): List<List<Int>> {
            return listOf(
                listOf(-1),
                listOf(),
                listOf(100000, Int.MAX_VALUE),
            )
        }
    }

    private val charactersRepository = mock<CharactersRepository>()
    private lateinit var getEpisodesList: GetEpisodesList

    @BeforeEach
    fun setUp() {
        getEpisodesList = GetEpisodesList(charactersRepository)
    }

    @AfterEach
    fun tearDown() {
        reset(charactersRepository)
    }

    @ParameterizedTest
    @MethodSource("existingIdsProvider")
    fun `invoke should return episodes list`(urlsInt: List<Int>): Unit = runBlocking {
        // Given
        val expected: List<Episode> = List(urlsInt.size) { createMockEpisode(urlsInt[it]) }
        val urlsList: List<String> = List(urlsInt.size) { "https://rickandmortyapi.com/api/character/${urlsInt[it]}" }

        `when`(charactersRepository.getEpisodesList(urlsList = urlsList)).thenReturn(flowOf(expected))

        // When
        val flowResult = getEpisodesList.invoke(urlsList = urlsList)

        // Then
        var actual: List<Episode>? = null
        flowResult.collect { pagingData ->
            actual = pagingData
        }

        Assertions.assertEquals(actual, expected)
        verify(charactersRepository, times(1)).getEpisodesList(urlsList = urlsList)
    }


    @ParameterizedTest
    @MethodSource("notExistingIdsProvider")
    fun `invoke should return null episodes`(urlsInt: List<Int>): Unit = runBlocking {
        // Given
        val expected = null
        val urlsList: List<String> = List(urlsInt.size) { "https://rickandmortyapi.com/api/character/${urlsInt[it]}" }

        `when`(charactersRepository.getEpisodesList(urlsList = urlsList)).thenReturn(flowOf(expected))

        // When
        val flowResult = getEpisodesList.invoke(urlsList = urlsList)

        // Then
        var actual: List<Episode>? = null
        flowResult.collect { pagingData ->
            actual = pagingData
        }

        Assertions.assertEquals(actual, expected)
        verify(charactersRepository, times(1)).getEpisodesList(urlsList = urlsList)
    }
}