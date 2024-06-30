package com.evg.characters.domain.usecase

import com.evg.characters.domain.model.Character
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

class GetCharacterByIdTest {
    private val charactersRepository = mock<CharactersRepository>()
    private lateinit var getCharacterById: GetCharacterById

    @BeforeEach
    fun setUp() {
        getCharacterById = GetCharacterById(charactersRepository)
    }

    @AfterEach
    fun tearDown() {
        reset(charactersRepository)
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 3, 100])
    fun `invoke should return character by id`(characterId: Int): Unit = runBlocking {
        // Given
        val expected = createMockCharacter(id = characterId)

        `when`(charactersRepository.getCharacterById(id = characterId)).thenReturn(flowOf(expected))

        // When
        val flowResult = getCharacterById.invoke(id = characterId)

        // Then
        var actual: Character? = null
        flowResult.collect { pagingData ->
            actual = pagingData
        }

        Assertions.assertEquals(actual, expected)
        verify(charactersRepository, times(1)).getCharacterById(id = characterId)
    }

    @ParameterizedTest
    @ValueSource(ints = [-1, 0, Int.MAX_VALUE])
    fun `invoke should return null when character not found`(characterId: Int): Unit = runBlocking {
        // Given
        val expected = null

        `when`(charactersRepository.getCharacterById(id = characterId)).thenReturn(flowOf(expected))

        // When
        val flowResult = getCharacterById.invoke(id = characterId)

        // Then
        var actual: Character? = null
        flowResult.collect { pagingData ->
            actual = pagingData
        }

        Assertions.assertEquals(actual, expected)
        verify(charactersRepository, times(1)).getCharacterById(id = characterId)
    }
}