package com.example.plovesphotography.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.plovesphotography.composablesUi.SplitFlapTextFastFlip

@Composable
fun FavoritesScreen() {
    Box (
        modifier = Modifier
            .padding(start = 20.dp, top = 20.dp)
    ){
        SplitFlapTextFastFlip(
            startText = "お気に入り", // Japanese for Homepage
            endText = "FAVORITES",   // Final text
        )
    }
}