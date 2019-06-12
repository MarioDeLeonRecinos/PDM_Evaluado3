package com.echavez.labo3evaluado.activities

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.echavez.labo3evaluado.AppConstants
import com.echavez.labo3evaluado.Entities.MoviePreview
import com.echavez.labo3evaluado.ViewModel.MovieViewModel
import com.echavez.labo3evaluado.adapters.PreviewAdapter
import kotlinx.android.synthetic.main.preview_add_movie.*
import android.net.NetworkInfo
import android.content.Context.CONNECTIVITY_SERVICE
import androidx.core.content.ContextCompat.getSystemService
import android.net.ConnectivityManager
import android.content.Context
import com.echavez.labo3evaluado.R


class NewMovieActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.preview_add_movie)

        val MovieViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)

        val layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        val recyclerView = rv_preview
        val moviesPreviewAdapter = PreviewAdapter(movies = AppConstants.emptymoviespreview,
            clickListener = { movie: MoviePreview, checky: View ->
                movie.selected = !movie.selected
                Toast.makeText(this, if (movie.selected) "Selected ${movie.Title}" else "Unselected ${movie.Title}", Toast.LENGTH_SHORT).show()
                if (movie.selected) checky.visibility = View.VISIBLE else checky.visibility = View.GONE
            })

        recyclerView.apply {
            adapter = moviesPreviewAdapter
            setHasFixedSize(true)
            this.layoutManager = layoutManager
        }

        MovieViewModel.getMovieListVM().observe(this, Observer { result ->
            moviesPreviewAdapter.changeDataSet(result)
        })

        bt_search.setOnClickListener {
            val movieNameQuery = et_search.text.toString()
            if (movieNameQuery.isNotEmpty() && movieNameQuery.isNotBlank()) {
                MovieViewModel.fetchMovie(movieNameQuery)
                MovieViewModel.getMovieListVM().observe(this, Observer { result ->
                    moviesPreviewAdapter.changeDataSet(result)
                })
            }
        }

        bt_cancel.setOnClickListener {clearView(et_search, moviesPreviewAdapter)}

        bt_add_preview.setOnClickListener {
            val thenownow = MovieViewModel.getMovieListVM().value
            val selectedMovies = thenownow?.filter { it.selected } ?: AppConstants.emptymoviespreview

            for (movie in selectedMovies) {
                MovieViewModel.fetchMovieByTitle(movie.Title)
                MovieViewModel.getMovieResult().observe(this, Observer {resultMovie ->
                    MovieViewModel.insert(resultMovie)
                })
            }
            clearView(et_search, moviesPreviewAdapter)

            setResult(Activity.RESULT_OK)
            finish()
        }

        val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val activeNetwork = cm.activeNetworkInfo
        val isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting
        if (isConnected){
            Toast.makeText(this,"Lol",Toast.LENGTH_SHORT)
        }

    }

    fun clearView(et: EditText, adapter: PreviewAdapter){
        et.text.clear()
        adapter.changeDataSet(AppConstants.emptymoviespreview)
    }


}