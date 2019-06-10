package com.echavez.labo3evaluado.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.echavez.labo3evaluado.Dao.MovieDao
import com.echavez.labo3evaluado.Entities.Movie

class MovieRepository (private val movieDao: MovieDao){

    @WorkerThread
    suspend fun insert(movie: Movie) = movieDao.insertMovie(movie)

    fun getAllfromMovies(): LiveData<List<Movie>> = movieDao.getAllMovies()

    fun getMovieByName(name: String) = movieDao.searchMovieByName(name)
}