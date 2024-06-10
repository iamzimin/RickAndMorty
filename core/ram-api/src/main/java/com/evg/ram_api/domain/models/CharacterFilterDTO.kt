package com.evg.ram_api.domain.models

data class CharacterFilterDTO(
    var name: String? = null,
    var status: StatusTypeDTO? = null,
    var species: String? = null,
    var type: String? = null,
    var gender: GenderTypeDTO? = null,
)

enum class StatusTypeDTO {
    ALIVE,
    DEAD,
    UNKNOWN,
}

enum class GenderTypeDTO {
    FEMALE,
    MALE,
    GENDERLESS,
    UNKNOWN,
}
