package com.evg.characters.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.evg.characters.domain.model.GenderType
import com.evg.characters.domain.model.StatusType
import com.evg.resource.FilterDialog
import com.evg.resource.model.FilterData
import com.evg.resource.model.FilterHeader

@Composable
fun CharacterFilterDialog(
    hideDialog: () -> Unit,
    onStatusChange: ((StatusType?) -> Unit)? = null,
    onSpeciesChange: ((String?) -> Unit)? = null,
    onGenderChange: ((GenderType?) -> Unit)? = null,
) {
    var selectedStatus: StatusType? by remember { mutableStateOf(null) }
    var selectedSpecies: String? by remember { mutableStateOf(null) }
    var selectedGender: GenderType? by remember { mutableStateOf(null) }

    FilterDialog(
        onDismiss = {
            selectedStatus = null
            selectedSpecies = null
            selectedGender = null
            onStatusChange?.let { it(null) }
            onSpeciesChange?.let { it(null) }
            onGenderChange?.let { it(null) }
            hideDialog()
        },
        onApplyFilter = {
            onStatusChange?.let { it(selectedStatus) }
            onSpeciesChange?.let { it(selectedSpecies) }
            onGenderChange?.let { it(selectedGender) }
            hideDialog()
        },
        header = "Filter characters",
        filters = listOf(
            FilterHeader(
                title = "Status type",
                filters = listOf(
                    FilterData(
                        text = "Alive",
                        onClick = { selectedStatus = StatusType.ALIVE },
                    ),
                    FilterData(
                        text = "Dead",
                        onClick = { selectedStatus = StatusType.DEAD },
                    ),
                    FilterData(
                        text = "Unknown",
                        onClick = { selectedStatus = StatusType.UNKNOWN },
                    ),
                )
            ),
            FilterHeader(
                title = "Character species",
                filters = listOf(
                    FilterData(
                        text = "Human",
                        onClick = { selectedSpecies = "Human" },
                    ),
                    FilterData(
                        text = "Alien",
                        onClick = { selectedSpecies = "Alien" },
                    ),
                    FilterData(
                        text = "Humanoid",
                        onClick = { selectedSpecies = "Humanoid" },
                    ),
                    FilterData(
                        text = "Poopybutthole",
                        onClick = { selectedSpecies = "Poopybutthole" },
                    ),
                    FilterData(
                        text = "Mythological Creature",
                        onClick = { selectedSpecies = "Mythological Creature" },
                    ),
                    FilterData(
                        text = "Animal",
                        onClick = { selectedSpecies = "Animal" },
                    ),
                    FilterData(
                        text = "Robot",
                        onClick = { selectedSpecies = "Robot" },
                    ),
                    FilterData(
                        text = "Cronenberg",
                        onClick = { selectedSpecies = "Cronenberg" },
                    ),
                    FilterData(
                        text = "Disease",
                        onClick = { selectedSpecies = "Disease" },
                    ),
                    FilterData(
                        text = "Unknown",
                        onClick = { selectedSpecies = "Unknown" },
                    ),
                )
            ),
            FilterHeader(
                title = "Gender type",
                filters = listOf(
                    FilterData(
                        text = "Female",
                        onClick = { selectedGender = GenderType.FEMALE },
                    ),
                    FilterData(
                        text = "Male",
                        onClick = { selectedGender = GenderType.MALE },
                    ),
                    FilterData(
                        text = "Genderless",
                        onClick = { selectedGender = GenderType.GENDERLESS },
                    ),
                    FilterData(
                        text = "Unknown",
                        onClick = { selectedGender = GenderType.UNKNOWN },
                    ),
                )
            ),
        ),
    )
}