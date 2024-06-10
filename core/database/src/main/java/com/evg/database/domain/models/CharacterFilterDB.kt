package com.evg.database.domain.models

data class CharacterFilterDB(
    var name: String? = null,
    var status: StatusTypeDB? = null,
    var species: String? = null,
    var type: String? = null,
    var gender: GenderTypeDB? = null,
)

enum class StatusTypeDB {
    ALIVE,
    DEAD,
    UNKNOWN,
}

enum class GenderTypeDB {
    FEMALE,
    MALE,
    GENDERLESS,
    UNKNOWN,
}

