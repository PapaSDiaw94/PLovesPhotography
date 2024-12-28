package com.example.plovesphotography

import android.os.Bundle
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.plovesphotography.screens.AboutScreen
import com.example.plovesphotography.screens.CategoryScreen
import com.example.plovesphotography.screens.FavoritesScreen
import com.example.plovesphotography.screens.HomeScreen
import com.example.plovesphotography.ui.theme.DarkGray
import com.example.plovesphotography.ui.theme.DarkSalmon
import com.example.plovesphotography.ui.theme.PLovesPhotographyTheme
import com.example.plovesphotography.ui.theme.Valspar
import com.exyte.animatednavbar.AnimatedNavigationBar
import com.exyte.animatednavbar.animation.balltrajectory.Teleport
import com.exyte.animatednavbar.animation.indendshape.Height
import com.exyte.animatednavbar.animation.indendshape.shapeCornerRadius
import com.exyte.animatednavbar.items.wigglebutton.WiggleButton

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PLovesPhotographyTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            AnimatedNavigationBar(
                navController = navController,
                items = listOf(
                    BottomNavItems.Home,
                    BottomNavItems.Categories,
                    BottomNavItems.Favorites,
                    BottomNavItems.About
                )
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItems.Home.route,
            Modifier.padding(innerPadding)
        ) {
            composable(BottomNavItems.Home.route) { HomeScreen() }
            composable(BottomNavItems.Categories.route) { CategoryScreen() }
            composable(BottomNavItems.Favorites.route) { FavoritesScreen() }
            composable(BottomNavItems.About.route) { AboutScreen() }
        }
    }
}

@Composable
fun AnimatedNavigationBar(
    navController: NavController,
    items: List<BottomNavItems>
) {
    var selectedIndex by remember { mutableStateOf(0) }

        AnimatedNavigationBar(
            modifier = Modifier
                .height(85.dp),
            selectedIndex = selectedIndex,
            barColor = Valspar, // Use a custom color
            ballColor = DarkSalmon,
            cornerRadius = shapeCornerRadius(25.dp),
            ballAnimation = Teleport(tween(500, easing = LinearEasing)),
            indentAnimation = Height(
                indentWidth = 56.dp,
                indentHeight = 15.dp,
                animationSpec = tween(
                    1000,
                    easing = { OvershootInterpolator().getInterpolation(it) })
            )
        ) {
            items.forEachIndexed { index, item ->
                WiggleButton(
                    modifier = Modifier.fillMaxSize(),
                    isSelected = selectedIndex == index,
                    onClick = {
                        selectedIndex = index
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = item.icon,
                    backgroundIcon = item.icon,
                    outlineColor = DarkGray,

                )
            }
        }
}
