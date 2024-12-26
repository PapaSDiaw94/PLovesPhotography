package com.example.plovesphotography

sealed class BottomNavItems (val route:String, val icon:Int,val label:String){
    object Home: BottomNavItems("home",R.drawable.ic_home,"Home")
    object Categories: BottomNavItems("categories",R.drawable.ic_categories,"Categories")
    object Favorites : BottomNavItems("favorites", R.drawable.ic_favorites, "Favorites")
    object About : BottomNavItems("about", R.drawable.ic_about, "Contact Me")

}