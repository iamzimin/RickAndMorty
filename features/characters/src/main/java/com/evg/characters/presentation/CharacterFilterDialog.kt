package com.evg.characters.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.evg.characters.domain.model.GenderType
import com.evg.characters.domain.model.StatusType
import com.evg.characters.presentation.viewmodel.CharactersViewModel
import com.evg.resource.FilterDialog
import com.evg.resource.model.FilterData
import com.evg.resource.model.FilterHeader
import com.evg.resource.theme.RickAndMortyTheme

@Composable
fun CharacterFilterDialog(
    hideDialog: () -> Unit,
    onStatusChange: ((StatusType?) -> Unit)? = null,
    onSpeciesChange: ((String?) -> Unit)? = null,
    onGenderChange: ((GenderType?) -> Unit)? = null,
    viewModel: CharactersViewModel? = null,
) {
    FilterDialog(
        onDismiss = {
            onStatusChange?.let { it(null) }
            onSpeciesChange?.let { it(null) }
            onGenderChange?.let { it(null) }
            hideDialog()
        },
        onApplyFilter = {
            onStatusChange?.let { it(viewModel?.selectedStatus) }
            onSpeciesChange?.let { it(viewModel?.selectedSpecies) }
            onGenderChange?.let { it(viewModel?.selectedGender) }
            hideDialog()
        },
        header = "Filter characters",
        filters = listOf(
            FilterHeader(
                title = "Status type",
                filters = listOf(
                    FilterData(
                        text = "Alive",
                        color = Color.Green,
                        selected = viewModel?.selectedStatus == StatusType.ALIVE,
                        onClick = { viewModel?.selectedStatus = StatusType.ALIVE },
                    ),
                    FilterData(
                        text = "Dead",
                        color = Color.Red,
                        selected = viewModel?.selectedStatus == StatusType.DEAD,
                        onClick = { viewModel?.selectedStatus = StatusType.DEAD },
                    ),
                    FilterData(
                        text = "Unknown",
                        color = Color.Black,
                        selected = viewModel?.selectedStatus == StatusType.UNKNOWN,
                        onClick = { viewModel?.selectedStatus = StatusType.UNKNOWN },
                    ),
                )
            ),
            FilterHeader(
                title = "Character species",
                filters = listOf(
                    FilterData(
                        text = "Human",
                        selected = viewModel?.selectedSpecies == "Human",
                        onClick = { viewModel?.selectedSpecies = "Human" },
                    ),
                    FilterData(
                        text = "Alien",
                        selected = viewModel?.selectedSpecies == "Alien",
                        onClick = { viewModel?.selectedSpecies = "Alien" },
                    ),
                    FilterData(
                        text = "Humanoid",
                        selected = viewModel?.selectedSpecies == "Humanoid",
                        onClick = { viewModel?.selectedSpecies = "Humanoid" },
                    ),
                    FilterData(
                        text = "Poopybutthole",
                        selected = viewModel?.selectedSpecies == "Poopybutthole",
                        onClick = { viewModel?.selectedSpecies = "Poopybutthole" },
                    ),
                    FilterData(
                        text = "Mythological Creature",
                        selected = viewModel?.selectedSpecies == "Mythological Creature",
                        onClick = { viewModel?.selectedSpecies = "Mythological Creature" },
                    ),
                    FilterData(
                        text = "Animal",
                        selected = viewModel?.selectedSpecies == "Animal",
                        onClick = { viewModel?.selectedSpecies = "Animal" },
                    ),
                    FilterData(
                        text = "Robot",
                        selected = viewModel?.selectedSpecies == "Robot",
                        onClick = { viewModel?.selectedSpecies = "Robot" },
                    ),
                    FilterData(
                        text = "Cronenberg",
                        selected = viewModel?.selectedSpecies == "Cronenberg",
                        onClick = { viewModel?.selectedSpecies = "Cronenberg" },
                    ),
                    FilterData(
                        text = "Disease",
                        selected = viewModel?.selectedSpecies == "Disease",
                        onClick = { viewModel?.selectedSpecies = "Disease" },
                    ),
                    FilterData(
                        text = "Unknown",
                        selected = viewModel?.selectedSpecies == "Unknown",
                        onClick = { viewModel?.selectedSpecies = "Unknown" },
                    ),
                )
            ),
            FilterHeader(
                title = "Gender type",
                filters = listOf(
                    FilterData(
                        text = "Female",
                        color = Color.Red,
                        selected = viewModel?.selectedGender == GenderType.FEMALE,
                        onClick = { viewModel?.selectedGender = GenderType.FEMALE },
                    ),
                    FilterData(
                        text = "Male",
                        color = Color.Blue,
                        selected = viewModel?.selectedGender == GenderType.MALE,
                        onClick = { viewModel?.selectedGender = GenderType.MALE },
                    ),
                    FilterData(
                        text = "Genderless",
                        color = Color.Yellow,
                        selected = viewModel?.selectedGender == GenderType.GENDERLESS,
                        onClick = { viewModel?.selectedGender = GenderType.GENDERLESS },
                    ),
                    FilterData(
                        text = "Unknown",
                        color = Color.Black,
                        selected = viewModel?.selectedGender == GenderType.UNKNOWN,
                        onClick = { viewModel?.selectedGender = GenderType.UNKNOWN },
                    ),
                )
            ),
        ),
    )
}

@Composable
@Preview(showBackground = true)
fun CharacterFilterDialogPreview() {
    RickAndMortyTheme {
        CharacterFilterDialog(
            hideDialog = {},
        )
    }
}