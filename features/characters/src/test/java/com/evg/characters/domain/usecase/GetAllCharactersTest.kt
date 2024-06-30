package com.evg.characters.domain.usecase

import androidx.paging.PagingData
import com.evg.characters.domain.model.Character
import com.evg.characters.domain.model.CharacterFilter
import com.evg.characters.domain.model.CharacterGender
import com.evg.characters.domain.model.CharacterLocation
import com.evg.characters.domain.model.CharacterOrigin
import com.evg.characters.domain.model.CharacterStatus
import com.evg.characters.domain.repository.CharactersRepository
import com.evg.characters.utils.MockFactory.createMockCharacter
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.mockito.Mockito.mock
import org.mockito.Mockito.reset
import org.mockito.Mockito.`when`
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

class GetAllCharactersTest {
    private val charactersRepository = mock<CharactersRepository>()
    private lateinit var getAllCharacters: GetAllCharacters

    @BeforeEach
    fun setUp() {
        getAllCharacters = GetAllCharacters(charactersRepository)
    }

    @AfterEach
    fun tearDown() {
        reset(charactersRepository)
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 1, 3, 100])
    fun `invoke should return characters from repository`(numberOfCharacters: Int): Unit = runBlocking {
        // Given
        val characters = List(numberOfCharacters) { createMockCharacter(it + 1) }
        val testFilter = CharacterFilter()
        val expected: PagingData<Character> = PagingData.from(characters)

        `when`(charactersRepository.getAllCharacters(filter = testFilter)).thenReturn(flowOf(expected))

        // When
        val flowResult = getAllCharacters.invoke(filter = testFilter)

        // Then
        var actual: PagingData<Character>? = null
        flowResult.collect { pagingData ->
            actual = pagingData
        }

        Assertions.assertEquals(actual, expected)
        verify(charactersRepository, times(1)).getAllCharacters(filter = testFilter)
    }
}