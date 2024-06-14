package com.evg.characters.domain.model

data class CharacterFilter(
    var name: String? = null,
    var status: StatusType? = null,
    var species: String? = null,
    var type: String? = null,
    var gender: GenderType? = null,
)

enum class StatusType {
    ALIVE,
    DEAD,
    UNKNOWN,
}

enum class GenderType {
    FEMALE,
    MALE,
    GENDERLESS,
    UNKNOWN,
}
