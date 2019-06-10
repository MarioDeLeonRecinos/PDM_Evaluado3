package com.echavez.labo3evaluado.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.echavez.labo3evaluado.Entities.Movie

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: Movie)

    @Query("select * from movie_table")
    fun getAllMovies(): LiveData<List<Movie>>

    @Query("select * from movie_table where Title like :name")
    fun searchMovieByName(name: String): LiveData<List<Movie>>

    @Query("DELETE FROM movie_table")
    fun deleteAll()

}