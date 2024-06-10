package com.evg.database.domain.mapper

import com.evg.database.domain.models.GenderTypeDB
import com.evg.database.domain.models.StatusTypeDB

internal fun StatusTypeDB?.toStringDB(): String? = when (this) {
    StatusTypeDB.ALIVE -> "Alive"
    StatusTypeDB.DEAD -> "Dead"
    StatusTypeDB.UNKNOWN -> "Unknown"
    else -> null
}

internal fun GenderTypeDB?.toStringDB(): String? = when (this) {
    GenderTypeDB.FEMALE -> "Female"
    GenderTypeDB.MALE -> "Male"
    GenderTypeDB.GENDERLESS -> "Genderless"
    GenderTypeDB.UNKNOWN -> "Unknown"
    else -> null
}