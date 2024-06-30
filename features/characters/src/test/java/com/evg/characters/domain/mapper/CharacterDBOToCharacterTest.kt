package com.evg.characters.domain.mapper

import com.evg.characters.domain.model.Character
import com.evg.characters.domain.model.CharacterGender
import com.evg.characters.domain.model.CharacterLocation
import com.evg.characters.domain.model.CharacterOrigin
import com.evg.characters.domain.model.CharacterStatus
import com.evg.database.domain.models.CharacterDBO
import com.evg.database.domain.models.CharacterLocationDBO
import com.evg.database.domain.models.CharacterOriginDBO
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class CharacterDBOToCharacterTest {

    companion object {
        @JvmStatic
        fun characterStatusProvider(): List<Arguments> {
            return listOf(
                Arguments.of("Alive", CharacterStatus.ALIVE),
                Arguments.of("Dead", CharacterStatus.DEAD),
                Arguments.of("Unknown", CharacterStatus.UNKNOWN),
                Arguments.of("???", CharacterStatus.UNKNOWN),
            )
        }
        @JvmStatic
        fun characterGenderProvider(): List<Arguments> {
            return listOf(
                Arguments.of("Female", CharacterGender.FEMALE),
                Arguments.of("Male", CharacterGender.MALE),
                Arguments.of("Genderless", CharacterGender.GENDERLESS),
                Arguments.of("Unknown", CharacterGender.UNKNOWN),
                Arguments.of("???", CharacterGender.UNKNOWN),
            )
        }
    }

    @ParameterizedTest
    @MethodSource("characterStatusProvider")
    fun `test CharacterDBO to Character conversion for different status`(statusString: String, statusEnum: CharacterStatus) {
        // Given
        val expected = Character(
            id = 1,
            name = "Name",
            status = statusEnum,
            species = "Species",
            type = "",
            gender = CharacterGender.MALE,
            origin = CharacterOrigin(
                name = "Origin",
                url = "https://rickandmortyapi.com/api/origin/1"
            ),
            location = CharacterLocation(
                name = "Location",
                url = "https://rickandmortyapi.com/api/location/1"
            ),
            image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            episode = listOf(
                "https://rickandmortyapi.com/api/episode/1",
                "https://rickandmortyapi.com/api/episode/2",
            ),
            url = "https://rickandmortyapi.com/api/character/1",
        )

        // When
        val actual = CharacterDBO(
            id = 1,
            name = "Name",
            status = statusString,
            species = "Species",
            type = "",
            gender = "Male",
            origin = CharacterOriginDBO(
                name = "Origin",
                url = "https://rickandmortyapi.com/api/origin/1"
            ),
            location = CharacterLocationDBO(
                name = "Location",
                url = "https://rickandmortyapi.com/api/location/1"
            ),
            image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            episode = listOf(
                "https://rickandmortyapi.com/api/episode/1",
                "https://rickandmortyapi.com/api/episode/2",
            ),
            url = "https://rickandmortyapi.com/api/character/1",
        ).toCharacter()

        // Then
        Assertions.assertEquals(actual, expected)
    }

    @ParameterizedTest
    @MethodSource("characterGenderProvider")
    fun `test CharacterDBO to Character conversion for different gender`(genderString: String, genderEnum: CharacterGender) {
        // Given
        val expected = Character(
            id = 1,
            name = "Name",
            status = CharacterStatus.ALIVE,
            species = "Species",
            type = "",
            gender = genderEnum,
            origin = CharacterOrigin(
                name = "Origin",
                url = "https://rickandmortyapi.com/api/origin/1"
            ),
            location = CharacterLocation(
                name = "Location",
                url = "https://rickandmortyapi.com/api/location/1"
            ),
            image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            episode = listOf(
                "https://rickandmortyapi.com/api/episode/1",
                "https://rickandmortyapi.com/api/episode/2",
            ),
            url = "https://rickandmortyapi.com/api/character/1",
        )

        // When
        val actual = CharacterDBO(
            id = 1,
            name = "Name",
            status = "Alive",
            species = "Species",
            type = "",
            gender = genderString,
            origin = CharacterOriginDBO(
                name = "Origin",
                url = "https://rickandmortyapi.com/api/origin/1"
            ),
            location = CharacterLocationDBO(
                name = "Location",
                url = "https://rickandmortyapi.com/api/location/1"
            ),
            image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            episode = listOf(
                "https://rickandmortyapi.com/api/episode/1",
                "https://rickandmortyapi.com/api/episode/2",
            ),
            url = "https://rickandmortyapi.com/api/character/1",
        ).toCharacter()

        // Then
        Assertions.assertEquals(actual, expected)
    }
}