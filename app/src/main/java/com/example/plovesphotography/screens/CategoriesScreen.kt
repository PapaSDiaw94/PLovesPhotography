package com.example.plovesphotography.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.plovesphotography.composablesUi.SplitFlapTextFastFlip

@Composable
fun CategoryScreen() {
    Box (
        modifier = Modifier
            .padding(start = 20.dp, top = 20.dp)
    ){
        SplitFlapTextFastFlip(
            startText = "CATEGORIES", // Japanese for Homepage
            endText = "CATEGORIES",   // Final text
        )
    }
}