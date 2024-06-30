package com.evg.characters.domain.mapper

import com.evg.characters.domain.model.CharacterFilter
import com.evg.characters.domain.model.GenderType
import com.evg.characters.domain.model.StatusType
import com.evg.database.domain.models.CharacterFilterDB
import com.evg.database.domain.models.GenderTypeDB
import com.evg.database.domain.models.StatusTypeDB
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class CharacterFilterToCharacterFilterDbTest {

    companion object {
        @JvmStatic
        fun characterStatusProvider(): List<Arguments> {
            return listOf(
                Arguments.of(StatusType.ALIVE, StatusTypeDB.ALIVE),
                Arguments.of(StatusType.DEAD, StatusTypeDB.DEAD),
                Arguments.of(StatusType.UNKNOWN, StatusTypeDB.UNKNOWN),
                Arguments.of(null, null),
            )
        }
        @JvmStatic
        fun characterGenderProvider(): List<Arguments> {
            return listOf(
                Arguments.of(GenderType.FEMALE, GenderTypeDB.FEMALE),
                Arguments.of(GenderType.MALE, GenderTypeDB.MALE),
                Arguments.of(GenderType.GENDERLESS, GenderTypeDB.GENDERLESS),
                Arguments.of(GenderType.UNKNOWN, GenderTypeDB.UNKNOWN),
                Arguments.of(null, null),
            )
        }
    }

    @ParameterizedTest
    @MethodSource("characterStatusProvider")
    fun `test CharacterResponse to Character conversion for different status`(statusType: StatusType?, statusTypeDB: StatusTypeDB?) {
        // Given
        val expected = CharacterFilterDB(
            name = "Name",
            status = statusTypeDB,
            species = "Species",
            type = "",
            gender = GenderTypeDB.MALE,
        )

        // When
        val actual = CharacterFilter(
            name = "Name",
            status = statusType,
            species = "Species",
            type = "",
            gender = GenderType.MALE,
        ).toCharacterFilterDB()

        // Then
        Assertions.assertEquals(actual, expected)
    }

    @ParameterizedTest
    @MethodSource("characterGenderProvider")
    fun `test CharacterResponse to Character conversion for different gender`(genderType: GenderType?, genderTypeDB: GenderTypeDB?) {
        // Given
        val expected = CharacterFilterDB(
            name = "Name",
            status = StatusTypeDB.ALIVE,
            species = "Species",
            type = "",
            gender = genderTypeDB,
        )

        // When
        val actual = CharacterFilter(
            name = "Name",
            status = StatusType.ALIVE,
            species = "Species",
            type = "",
            gender = genderType,
        ).toCharacterFilterDB()

        // Then
        Assertions.assertEquals(actual, expected)
    }
}