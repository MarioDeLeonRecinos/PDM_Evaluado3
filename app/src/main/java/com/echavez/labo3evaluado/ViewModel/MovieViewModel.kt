package com.echavez.labo3evaluado.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.echavez.labo3evaluado.Database.MovieDatabase
import com.echavez.labo3evaluado.Entities.Movie
import com.echavez.labo3evaluado.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel(application: Application): AndroidViewModel(application) {

    private val repository: MovieRepository
    val allMovies: LiveData<List<Movie>>

    init{
        val movieDao = MovieDatabase.getDatabase(application).movieDao()
        repository = MovieRepository(movieDao)
        allMovies = repository.getAllfromMovies()
    }

    fun insert(movie: Movie) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(movie)
    }

}