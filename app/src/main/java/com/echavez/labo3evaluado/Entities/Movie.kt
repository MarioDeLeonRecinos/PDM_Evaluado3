package com.echavez.labo3evaluado.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_table")
data class Movie (
    @PrimaryKey
    @ColumnInfo(name = "imdbID") val imdbID: String = "N/A",
    @ColumnInfo(name = "Title") val Title:String = "N/A",
    @ColumnInfo(name = "Year") val Year:String = "N/A",
    @ColumnInfo(name = "Released") val Released: String = "N/A",
    @ColumnInfo(name = "Runtime") val Runtime:String = "N/A",
    @ColumnInfo(name = "Genre") val Genre:String = "N/A",
    @ColumnInfo(name = "Director") val Director:String = "N/A",
    @ColumnInfo(name = "Actors") val Actors:String = "N/A",
    @ColumnInfo(name = "Plot") val Plot:String = "N/A",
    @ColumnInfo(name = "Language")val Language:String = "N/A",
    @ColumnInfo(name = "imdbRating") val imdbRating:String = "N/A",
    @ColumnInfo(name = "Poster") val Poster:String = "N/A"
)

data class MoviePreview(
    val Title: String = "N/A",
    val Year: String = "N/A",
    val imdbID: String = "N/A",
    val Type: String = "N/A",
    val Poster: String = "N/A",
    var selected: Boolean = false
)

data class OmbdMovieResponse (
    val Search: List<MoviePreview>,
    val totalResults: String,
    val Response: String
)