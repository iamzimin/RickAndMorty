package com.evg.resource

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.evg.resource.theme.RickAndMortyTheme

@Composable
fun SearchField(modifier: Modifier = Modifier.fillMaxWidth(), onValueChange: (String) -> Unit) {
    val cardHeight = 50.dp
    val textSize = 16.sp
    val imageSize = 30.dp
    var textState by remember {
        mutableStateOf(TextFieldValue())
    }

    TextField(
        modifier = modifier
            .height(cardHeight)
            .background(color = Color.Transparent)
            .border(
                BorderStroke(2.dp, Color.Black),
                shape = RoundedCornerShape(10.dp)
            ),
        value = textState,
        onValueChange = {
            newText -> textState = newText
            onValueChange(newText.text)
        },
        singleLine = true,
        textStyle = TextStyle(
            fontSize = textSize,
        ),
        placeholder = {
            Text(
                text = "Search",
                fontSize = textSize,
            )
        },
        leadingIcon = {
            Icon(
                painter = painterResource(
                    id = R.drawable.search,
                ),
                contentDescription = "Search",
                modifier = Modifier.size(imageSize),
            )
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = Color.Transparent,
        )
    )
}

@Preview(showBackground = true)
@Composable
fun SearchFieldPreview() {
    RickAndMortyTheme {
        SearchField(onValueChange = {})
    }
}