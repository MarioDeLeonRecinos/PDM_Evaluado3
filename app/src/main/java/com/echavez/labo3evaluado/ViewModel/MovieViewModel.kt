package com.echavez.labo3evaluado.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.echavez.labo3evaluado.Database.MovieDatabase
import com.echavez.labo3evaluado.Entities.Movie
import com.echavez.labo3evaluado.Entities.MoviePreview
import com.echavez.labo3evaluado.Network.ApiBody
import com.echavez.labo3evaluado.repository.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel(application: Application): AndroidViewModel(application) {

    private val repository: MovieRepository
    //val allMovies: LiveData<List<Movie>>

    init{
        val movieDao = MovieDatabase.getDatabase(application).movieDao()
        repository = MovieRepository(movieDao,ApiBody.API)
        //allMovies = repository.getAllfromMovies()
    }

    private val scope = CoroutineScope(Dispatchers.IO)

    private val movieslist = MutableLiveData<MutableList<MoviePreview>>()

    private val movieResult = MutableLiveData<Movie>()

    fun fetchMovie(name: String){
        scope.launch {
            val movies = repository.getMoviesByName(name)
            movieslist.postValue(movies?: arrayListOf(MoviePreview(Title = "Dummy 1"), MoviePreview(Title = "Dummy 2")))
        }
    }

    fun getMovieListVM(): LiveData<MutableList<MoviePreview>> = movieslist

    fun fetchMovieByTitle(name: String){
        scope.launch {
            val movie = repository.getMovieByTitle(name)
            movieResult.postValue(movie)
        }
    }

    fun getMovieResult(): LiveData<Movie> = movieResult

    fun insert(movie: Movie) = scope.launch {
        repository.insert(movie)
    }


    fun getAll(): LiveData<List<Movie>> = repository.getAllfromMovies()

    fun getMovieByName(name: String): LiveData<List<Movie>> = repository.getMovieByName(name)

}