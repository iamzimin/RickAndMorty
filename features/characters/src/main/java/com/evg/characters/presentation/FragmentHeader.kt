package com.evg.characters.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.evg.resource.ImageTitle
import com.evg.resource.R
import com.evg.resource.SearchField
import com.evg.resource.theme.RickAndMortyTheme

@Composable
fun FragmentHeader() {
    Column {
        ImageTitle(modifier = Modifier.align(alignment = Alignment.CenterHorizontally))
        Row(
            modifier = Modifier
                .padding(horizontal = 10.dp)
        ) {
            SearchField(modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.width(5.dp))
            Icon(
                painter = painterResource(id = R.drawable.filter),
                contentDescription = "Filter",
                modifier = Modifier
                    .height(50.dp)
                    .border(
                        BorderStroke(2.dp, Color.Black),
                        shape = RoundedCornerShape(10.dp),
                    )
                    .padding(all = 5.dp)
                    .clickable {

                    },
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFragmentHeader() {
    RickAndMortyTheme {
        FragmentHeader()
    }
}