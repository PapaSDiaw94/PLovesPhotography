package com.example.plovesphotography.composablesUi

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.plovesphotography.R
import com.example.plovesphotography.ui.theme.DarkSalmon
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun getRandomCharacter(): Char {
    // Combine English, Japanese, and Korean characters into a single pool
    val ranges = listOf(
        'A'..'Z',             // English uppercase letters
        '\u3040'..'\u30FF',   // Hiragana and Katakana (Japanese)
        '\uAC00'..'\uD7A3'    // Hangul Syllables (Korean)
    )
    // Randomly select a range and then a character from it
    val randomRange = ranges.random()
    return randomRange.random()
}

fun normalizeTextLength(startText: String, endText: String): Pair<String, String> {
    val maxLength = maxOf(startText.length, endText.length)
    val paddedStartText = startText.padEnd(maxLength, ' ') // Pad with spaces
    val paddedEndText = endText.padEnd(maxLength, ' ')     // Pad with spaces
    return paddedStartText to paddedEndText
}

@Composable
fun SplitFlapTextFastFlip(
    startText: String,
    endText: String,
    modifier: Modifier = Modifier,
    initialFlippingDuration: Int = 40,
    totalInitialFlips: Int = 10,
    sequentialFlipDuration: Int = 20,
    totalSequentialFlips: Int = 6,
    overlapDuration: Int = 90
) {
    val (normalizedStartText, normalizedEndText) = normalizeTextLength(startText, endText)
    val animatedText = remember { mutableStateOf(normalizedStartText) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(normalizedEndText) {
        scope.launch {
            val currentText = animatedText.value.toCharArray()

            // Step 1: All letters flip simultaneously
            for (flip in 1..totalInitialFlips) {
                for (i in currentText.indices) {
                    currentText[i] = getRandomCharacter()
                }
                animatedText.value = String(currentText)
                delay(initialFlippingDuration.toLong())
            }

            // Step 2: Letters form sequentially from left to right
            for (i in currentText.indices) {
                launch {
                    for (flip in 1..totalSequentialFlips) {
                        currentText[i] = if (flip == totalSequentialFlips) {
                            normalizedEndText[i]
                        } else {
                            getRandomCharacter()
                        }
                        animatedText.value = String(currentText)
                        delay(sequentialFlipDuration.toLong())
                    }
                }
                delay(overlapDuration.toLong())
            }
        }
    }

    // Stabilize the layout using a fixed box for each character
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        animatedText.value.forEach { char ->
            Box(
                modifier = Modifier
                    .width(20.dp) // Fixed width for each character
                    .height(30.dp), // Fixed height for each character
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = char.toString(),
                    color = DarkSalmon,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.gugi)),
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center
                    )
                )
            }
        }
    }
}