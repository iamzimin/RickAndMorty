package com.evg.characters.presentation

import android.content.res.Configuration
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
    fun onClickSelectedStatus(status: StatusType?): () -> Unit {
        return {
            viewModel?.selectedStatus = if (viewModel?.selectedStatus == status) null else status
        }
    }
    fun onClickSelectedSpecies(species: String?): () -> Unit {
        return {
            viewModel?.selectedSpecies = if (viewModel?.selectedSpecies == species) null else species
        }
    }
    fun onClickSelectedGender(genderType: GenderType?): () -> Unit {
        return {
            viewModel?.selectedGender = if (viewModel?.selectedGender == genderType) null else genderType
        }
    }

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
                        onClick = onClickSelectedStatus(StatusType.ALIVE),
                    ),
                    FilterData(
                        text = "Dead",
                        color = Color.Red,
                        selected = viewModel?.selectedStatus == StatusType.DEAD,
                        onClick = onClickSelectedStatus(StatusType.DEAD),
                    ),
                    FilterData(
                        text = "Unknown",
                        color = Color.Black,
                        selected = viewModel?.selectedStatus == StatusType.UNKNOWN,
                        onClick = onClickSelectedStatus(StatusType.UNKNOWN),
                    ),
                )
            ),
            FilterHeader(
                title = "Character species",
                filters = listOf(
                    FilterData(
                        text = "Human",
                        selected = viewModel?.selectedSpecies == "Human",
                        onClick = onClickSelectedSpecies("Human"),
                    ),
                    FilterData(
                        text = "Alien",
                        selected = viewModel?.selectedSpecies == "Alien",
                        onClick = onClickSelectedSpecies("Alien"),
                    ),
                    FilterData(
                        text = "Humanoid",
                        selected = viewModel?.selectedSpecies == "Humanoid",
                        onClick = onClickSelectedSpecies("Humanoid"),
                    ),
                    FilterData(
                        text = "Poopybutthole",
                        selected = viewModel?.selectedSpecies == "Poopybutthole",
                        onClick = onClickSelectedSpecies("Poopybutthole"),
                    ),
                    FilterData(
                        text = "Mythological Creature",
                        selected = viewModel?.selectedSpecies == "Mythological Creature",
                        onClick = onClickSelectedSpecies("Mythological Creature"),
                    ),
                    FilterData(
                        text = "Animal",
                        selected = viewModel?.selectedSpecies == "Animal",
                        onClick = onClickSelectedSpecies("Animal"),
                    ),
                    FilterData(
                        text = "Robot",
                        selected = viewModel?.selectedSpecies == "Robot",
                        onClick = onClickSelectedSpecies("Robot"),
                    ),
                    FilterData(
                        text = "Cronenberg",
                        selected = viewModel?.selectedSpecies == "Cronenberg",
                        onClick = onClickSelectedSpecies("Cronenberg"),
                    ),
                    FilterData(
                        text = "Disease",
                        selected = viewModel?.selectedSpecies == "Disease",
                        onClick = onClickSelectedSpecies("Disease"),
                    ),
                    FilterData(
                        text = "Unknown",
                        selected = viewModel?.selectedSpecies == "Unknown",
                        onClick = onClickSelectedSpecies("Unknown"),
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
                        onClick = onClickSelectedGender(GenderType.FEMALE),
                    ),
                    FilterData(
                        text = "Male",
                        color = Color.Blue,
                        selected = viewModel?.selectedGender == GenderType.MALE,
                        onClick = onClickSelectedGender(GenderType.MALE),
                    ),
                    FilterData(
                        text = "Genderless",
                        color = Color.Yellow,
                        selected = viewModel?.selectedGender == GenderType.GENDERLESS,
                        onClick = onClickSelectedGender(GenderType.GENDERLESS),
                    ),
                    FilterData(
                        text = "Unknown",
                        color = Color.Black,
                        selected = viewModel?.selectedGender == GenderType.UNKNOWN,
                        onClick = onClickSelectedGender(GenderType.UNKNOWN),
                    ),
                )
            ),
        ),
    )

}

@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun CharacterFilterDialogPreview() {
    RickAndMortyTheme {
        CharacterFilterDialog(
            hideDialog = {},
        )
    }
}