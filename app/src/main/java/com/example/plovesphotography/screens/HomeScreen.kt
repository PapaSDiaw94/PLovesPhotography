package com.example.plovesphotography.screens
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
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

import com.example.plovesphotography.composablesUi.SplitFlapTextFastFlip
import com.example.plovesphotography.ui.theme.DarkGray
import com.example.plovesphotography.ui.theme.DarkSalmon
import com.example.plovesphotography.ui.theme.Valspar

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Title with padding
        SplitFlapTextFastFlip(
            startText = "ホームページ", // Japanese for Homepage
            endText = "HOMEPAGE",   // Final text
            modifier = Modifier.padding( 20.dp) // Space below the title
        )

        // Card container
        Box(
            modifier = Modifier
                .fillMaxWidth() // Full width
                .padding(horizontal = 10.dp), // Horizontal padding for the Card
            contentAlignment = Alignment.Center // Center the Card horizontally
        ) {
            PictureOfTheDayCard(
                imageUrl = "https://picsum.photos/id/1084/536/354?grayscale",
                date = "12/12/2022",
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth() // Full width
                .padding(horizontal = 10.dp), // Horizontal padding for the Card
            contentAlignment = Alignment.Center // Center the Card horizontally
        ) {
            ImageInfoWithArrow(
                title = "Photo title #23",
                author = "author",
                onArrowClick = { /* Handle arrow click */ }
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth() // Full width
                .padding(horizontal = 10.dp), // Horizontal padding for the Card
            contentAlignment = Alignment.Center
        ) {

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
            .fillMaxWidth()
            .height(300.dp), // Adjust height based on your design
        elevation = 5.dp
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
                        fontWeight = FontWeight.Bold,
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
            .padding(end = 10.dp, top = 10.dp, bottom = 10.dp)
    ) {
        // Card for Title and Author
        Card(
            shape = RoundedCornerShape(16.dp),
            backgroundColor = Valspar, // Beige-like color
            elevation = 4.dp,
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
                        color = DarkGray
                    )
                )
                Text(
                    text = "by $author",
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.monomaniac1)),
                        fontWeight = FontWeight.Thin,
                        color = DarkGray
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
                .background(DarkSalmon) // Match arrow circle color
                .clickable { onArrowClick() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Arrow",
                tint = Color.White
            )
        }
    }
}



