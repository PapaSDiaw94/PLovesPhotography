package com.example.plovesphotography.screens
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter
import com.example.plovesphotography.R
import com.example.plovesphotography.composablesUi.ReusableCard

import com.example.plovesphotography.composablesUi.SplitFlapTextFastFlip
import com.example.plovesphotography.ui.theme.Black
import com.example.plovesphotography.ui.theme.DarkGray
import com.example.plovesphotography.ui.theme.DarkSalmon
import com.example.plovesphotography.ui.theme.GreenMint
import com.example.plovesphotography.ui.theme.GreyMint
import com.example.plovesphotography.ui.theme.Valspar
import com.example.plovesphotography.ui.theme.White

@Composable
fun HomeScreen() {
    // State to control if animation should play
    val playAnimation = remember { mutableStateOf(true) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 5.dp),
    ) {
        // Add other sections or headers as needed
        item {
            // Title with padding
            SplitFlapTextFastFlip(
                startText = "HOMEPAGE", // Japanese for Homepage
                endText = "HOMEPAGE",   // Final text
                modifier = Modifier.padding(20.dp), // Space below the title
                playAnimation = playAnimation.value, // Trigger animation only once
                onAnimationEnd = { playAnimation.value = false } // Stop animation after it plays
            )
        }
        // Add the "What's New" section
        item {
            Box(
                modifier = Modifier
                    .padding(5.dp)
            ) {
                PictureOfTheDayCard(
                    imageUrl = "https://picsum.photos/id/1084/536/354?grayscale",
                    date = "12/12/2022",
                )
            }
        }
        // Add any additional sections below
        item {
            Box(
                modifier = Modifier
                    .padding(start = 5.dp, end = 20.dp)
            ) {
                ImageInfoWithArrow(
                    title = "Photo title #23",
                    author = "author",
                    onArrowClick = { /* Handle arrow click */ }
                )
            }
        }
        // Add any additional sections below
        item {
            Box(
                modifier = Modifier
                    .padding(start = 20.dp, bottom = 5.dp)
            ) {
                Subtitle("WHAT'S NEW?")
            }
        }
        // Add any additional sections below
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp) // Constrain height of the grid
                    .padding(horizontal = 5.dp)
            ) {
                WhatsNewSection(cards)
            }
        }
    }
}

@Composable
fun PictureOfTheDayCard(
    imageUrl: String, // URL for the image
    date: String,     // Date the picture was taken
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .border(1.dp, Black, RoundedCornerShape(16.dp))
            .fillMaxWidth()
            .height(250.dp), // Adjust height based on your design
        elevation = 0.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            // Background Image
            Image(
                painter = rememberAsyncImagePainter(imageUrl),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            // Overlay content
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopStart)
                    .padding(16.dp)
            ) {
                Text(
                    text = "PICTURE OF THE DAY",
                    color = Color.White,
                    fontSize = 40.sp,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.monomaniac1)),
                        fontWeight = FontWeight.Normal,
                    )
                )
                Text(
                    text = date,
                    color = Color.White,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.monomaniac1)),
                        fontWeight = FontWeight.Bold,
                    )
                )
            }
        }
    }
}

@Composable
fun ImageInfoWithArrow(
    title: String,
    author: String,
    onArrowClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 10.dp, bottom = 10.dp)
    ) {
        // Card for Title and Author
        Card(
            shape = RoundedCornerShape(16.dp),
            backgroundColor = Black, // Beige-like color
            elevation = 0.dp,
            modifier = Modifier
                .widthIn(max = 320.dp) // Constrain width
                .padding(end = 56.dp) // Leave space for the circle
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = title,
                    fontSize = 24.sp,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.monomaniac1)),
                        fontWeight = FontWeight.Normal,
                        color = White
                    )
                )
                Text(
                    text = "by $author",
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.monomaniac1)),
                        fontWeight = FontWeight.ExtraLight,
                        color = White
                    )
                )
            }
        }

        // Circle with Arrow
        Box(
            modifier = Modifier
                .align(Alignment.CenterEnd) // Align the circle to the end
                .offset(x = 16.dp) // Offset to separate it visually
                .size(80.dp) // Slightly increase the circle size
                .clip(CircleShape)
                .background(GreenMint) // Match arrow circle color
                .clickable { onArrowClick() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Arrow",
                tint = White
            )
        }
    }
}

@Composable
fun Subtitle(subtitle:String){

    Text(
        text = subtitle,
        color = Black,
        fontSize = 20.sp,
        style = TextStyle(
            fontFamily = FontFamily(Font(R.font.gugi)),
            fontWeight = FontWeight.Normal,
        )
    )
}

@Composable
fun WhatsNewSection(cards: List<Pair<String, String>>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2), // Two cards per row
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(cards.size) { index -> // Use size of the list and iterate by index
            val (title, imageUrl) = cards[index] // Access the item by index
            ReusableCard(
                title = title,
                imageUrl = imageUrl,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}


val cards = listOf(
    "Visit at the Artech House" to "https://picsum.photos/536/354",
    "New Switch Release" to "https://picsum.photos/seed/picsum/536/354",
    "San Antonio Operations" to "https://picsum.photos/536/354",
    "Current Version and future Updates" to "https://picsum.photos/536/354"
)



