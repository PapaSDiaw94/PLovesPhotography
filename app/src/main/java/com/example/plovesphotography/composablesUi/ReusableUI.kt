package com.example.plovesphotography.composablesUi

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.plovesphotography.R
import com.example.plovesphotography.ui.theme.Black
import com.example.plovesphotography.ui.theme.GreenMint
import com.example.plovesphotography.ui.theme.White
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
    overlapDuration: Int = 90,
    playAnimation: Boolean = true, // Controls whether animation should play
    onAnimationEnd: () -> Unit = {} // Callback when animation finishes
) {
    val (normalizedStartText, normalizedEndText) = normalizeTextLength(startText, endText)
    val animatedText = remember { mutableStateOf(normalizedStartText) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(playAnimation) {
        if (playAnimation) {
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

                // Notify that the animation has ended
                onAnimationEnd()
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
                    color = GreenMint,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.gugi)),
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp,
                        textAlign = TextAlign.Center
                    )
                )
            }
        }
    }
}

@Composable
fun ReusableCard(
    title: String,
    imageUrl: String,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f) // Adjust for a card-like shape
            .clip(RoundedCornerShape(16.dp))
            .border(1.dp, Black, RoundedCornerShape(16.dp))
            .clickable(enabled = onClick != null) { onClick?.invoke() }
    ) {
        // Background Image with grayscale, blur, and darkening effects
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .blur(1.dp) // Slight blur effect
                .graphicsLayer(alpha = 1f), // Darken the image
            contentScale = ContentScale.Crop,
            colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply {
                setToSaturation(1f) // Apply grayscale
            })
        )

        // Title
        Text(
            text = title,
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.monomaniac1)),
                fontWeight = FontWeight.ExtraLight,
                fontSize = 25.sp,
                color = Black
            ),
            modifier = Modifier.align(Alignment.Center),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun BottomSheetContent(
    title: String,
    imageUrl: String,
    content: String,
    modifier: Modifier = Modifier,
    onClose: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(800.dp)
            .background(White, shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(White)
        ) {
            // Close Button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                contentAlignment = Alignment.TopEnd
            ) {
                IconButton(onClick = onClose) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close"
                    )
                }
            }

            // Image
            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .height(300.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            // Title
            Text(
                text = title,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                ),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            // Content
            Text(
                text = content,
                style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 22.sp,
                    color = Color.Gray
                ),
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}
