package com.evg.characters.presentation

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.evg.characters.presentation.mapper.toCharacterUI
import com.evg.characters.presentation.viewmodel.CharactersViewModel
import com.evg.resource.CharacterCard
import com.evg.resource.theme.RickAndMortyTheme
import com.evg.resource.R
import com.evg.resource.theme.BorderRadius
import com.evg.resource.theme.EdgesMargin
import com.evg.resource.theme.IconSize
import com.evg.resource.theme.VerticalSpacerPadding


@Composable
fun CharactersScreen(
    viewModel: CharactersViewModel = hiltViewModel<CharactersViewModel>(),
) {
    val characters = viewModel.characters.collectAsLazyPagingItems()
    var isShowDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.padding(horizontal = EdgesMargin)
    ) {
        FragmentHeader(
            onSearchTextChanged = { newName ->
                viewModel.setNameFilter(name = newName)
            },
            filterButton = {
                IconButton(
                    onClick = {
                        isShowDialog = true
                    },
                    modifier = Modifier
                        .size(IconSize)
                        .border(
                            BorderStroke(2.dp, MaterialTheme.colorScheme.outline),
                            shape = RoundedCornerShape(BorderRadius),
                        )
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.filter),
                        tint = MaterialTheme.colorScheme.inverseSurface,
                        contentDescription = "Filter",
                        modifier = Modifier.size(35.dp)
                    )
                }
                if (isShowDialog) {
                    CharacterFilterDialog(
                        hideDialog = { isShowDialog = false },
                        onGenderChange = {
                            viewModel.setCharacterGender(it)
                        },
                        onSpeciesChange = {
                            viewModel.setCharacterSpecies(it)
                        },
                        onStatusChange = {
                            viewModel.setCharacterStatus(it)
                        },
                        viewModel = viewModel,
                    )
                }
            }
        )
            LazyColumn {
                items(
                    count = characters.itemCount,
                    key = characters.itemKey { it.id },
                    contentType = characters.itemContentType { it.image },
                ) { index ->
                    val item = characters[index]
                    if (item != null) {
                        CharacterCard(
                            characterUI = item.toCharacterUI()
                        )
                    }
                }
            }
    }
}

@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun HomeScreenPreview() {
    RickAndMortyTheme {
        CharactersScreen()
    }
}