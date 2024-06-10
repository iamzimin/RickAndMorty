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

/*sealed class CharacterFilter {
    data class Name(val name: String) : CharacterFilter()
    data class Status(val status: StatusType) : CharacterFilter()
    data class Species(val species: String) : CharacterFilter()
    data class Type(val type: String) : CharacterFilter()
    data class Gender(val gender: GenderType) : CharacterFilter()
}*/