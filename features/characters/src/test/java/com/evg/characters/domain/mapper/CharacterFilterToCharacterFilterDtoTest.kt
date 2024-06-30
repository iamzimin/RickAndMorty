package com.evg.characters.domain.mapper

import com.evg.characters.domain.model.CharacterFilter
import com.evg.characters.domain.model.GenderType
import com.evg.characters.domain.model.StatusType
import com.evg.ram_api.domain.models.CharacterFilterDTO
import com.evg.ram_api.domain.models.GenderTypeDTO
import com.evg.ram_api.domain.models.StatusTypeDTO
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class CharacterFilterToCharacterFilterDtoTest {

    companion object {
        @JvmStatic
        fun characterStatusProvider(): List<Arguments> {
            return listOf(
                Arguments.of(StatusType.ALIVE, StatusTypeDTO.ALIVE),
                Arguments.of(StatusType.DEAD, StatusTypeDTO.DEAD),
                Arguments.of(StatusType.UNKNOWN, StatusTypeDTO.UNKNOWN),
                Arguments.of(null, null),
            )
        }
        @JvmStatic
        fun characterGenderProvider(): List<Arguments> {
            return listOf(
                Arguments.of(GenderType.FEMALE, GenderTypeDTO.FEMALE),
                Arguments.of(GenderType.MALE, GenderTypeDTO.MALE),
                Arguments.of(GenderType.GENDERLESS, GenderTypeDTO.GENDERLESS),
                Arguments.of(GenderType.UNKNOWN, GenderTypeDTO.UNKNOWN),
                Arguments.of(null, null),
            )
        }
    }

    @ParameterizedTest
    @MethodSource("characterStatusProvider")
    fun `test CharacterResponse to Character conversion for different status`(statusType: StatusType?, statusTypeDTO: StatusTypeDTO?) {
        // Given
        val expected = CharacterFilterDTO(
            name = "Name",
            status = statusTypeDTO,
            species = "Species",
            type = "",
            gender = GenderTypeDTO.MALE,
        )

        // When
        val actual = CharacterFilter(
            name = "Name",
            status = statusType,
            species = "Species",
            type = "",
            gender = GenderType.MALE,
        ).toCharacterFilterDTO()

        // Then
        Assertions.assertEquals(actual, expected)
    }

    @ParameterizedTest
    @MethodSource("characterGenderProvider")
    fun `test CharacterResponse to Character conversion for different gender`(genderType: GenderType?, genderTypeDTO: GenderTypeDTO?) {
        // Given
        val expected = CharacterFilterDTO(
            name = "Name",
            status = StatusTypeDTO.ALIVE,
            species = "Species",
            type = "",
            gender = genderTypeDTO,
        )

        // When
        val actual = CharacterFilter(
            name = "Name",
            status = StatusType.ALIVE,
            species = "Species",
            type = "",
            gender = genderType,
        ).toCharacterFilterDTO()

        // Then
        Assertions.assertEquals(actual, expected)
    }
}