package com.echavez.labo3evaluado.fragments


import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.echavez.labo3evaluado.AppConstants
import com.echavez.labo3evaluado.Entities.Movie
import com.echavez.labo3evaluado.R
import com.echavez.labo3evaluado.ViewModel.MovieViewModel
import com.echavez.labo3evaluado.adapters.MovieAdapter
import kotlinx.android.synthetic.main.movies_list_fragment.view.*


class MovieListFragment : Fragment() {

    private lateinit var movieViewModel: MovieViewModel
    private lateinit var moviesAdapter: MovieAdapter
    var listenerTool: ClickedMovieListener? = null

    interface ClickedMovieListener {
        fun managePortraitItemClick(movie: Movie)
        fun managedLandscapeItemClick(movie: Movie)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toast.makeText(activity, "ADVERTENCIA: No presionar el boton hacia atras al seleccionar una pelicula guardada porque no regresa al main.", Toast.LENGTH_LONG).show()
        setHasOptionsMenu(true)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ClickedMovieListener) {
            listenerTool = context
        } else {
            throw RuntimeException("Implementar interfaz")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listenerTool = null
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.movies_list_fragment, container, false)

        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)

        initRecyclerView(resources.configuration.orientation, view)

        movieViewModel.getAll().observe(this, Observer { result ->
            moviesAdapter.changeDataSet(result)
        })

        return view
    }

    fun initRecyclerView(orientation: Int, container: View) {
        val linearLayoutManager = LinearLayoutManager(this.context)
        val recyclerview = container.rv_list

        moviesAdapter = if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            MovieAdapter(
                movies = AppConstants.emptymovies,
                clickListener = { movie: Movie -> listenerTool?.managePortraitItemClick(movie) })
        } else {
            MovieAdapter(
                movies = AppConstants.emptymovies,
                clickListener = { movie: Movie -> listenerTool?.managedLandscapeItemClick(movie) })
        }

        recyclerview.apply {
            adapter = moviesAdapter
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
        }
    }

    private fun queryToDatabase(query: String) = movieViewModel.getMovieByName("%$query%").observe(this,
        Observer { queryResult -> moviesAdapter.changeDataSet(queryResult) })
}
