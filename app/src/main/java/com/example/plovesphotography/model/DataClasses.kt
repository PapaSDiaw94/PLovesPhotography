package com.example.plovesphotography.model

data class Photo(
    val id: String,                 // Unique identifier for the photo
    val title: String,              // Title of the photo
    val author: String,             // Name of the person who took the photo
    val dateTaken: String,          // Date the photo was taken (could use LocalDate for better type safety)
    val description: String,        // Description of the photo
    val url: String                 // URL to the photo resource
)

data class News(
    val id: String,                     // Unique identifier for the news item
    val title: String,                  // Title of the news
    val mainPhoto: String,              // URL of the main photo associated with the news
    val date: String,                   // Date the news was published or occurred
    val description: String,            // Description or body of the news
    val photoGallery: List<String>      // List of URLs for additional pictures related to the news
)