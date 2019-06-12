package com.echavez.labo3evaluado.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.echavez.labo3evaluado.Entities.Movie
import com.echavez.labo3evaluado.R
import kotlinx.android.synthetic.main.movie_viewer.view.*


class MovieViewerFragment: Fragment() {

    var movie = Movie()

    companion object {
        fun newInstance(dataset: Movie): MovieViewerFragment {
            return MovieViewerFragment().apply {
                movie = dataset
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.movie_viewer, container, false)
        bindData(view, movie)

        return view
    }

    fun bindData(view: View, data: Movie){
        Glide.with(this)
            .load(data.Poster)
            .placeholder(R.drawable.ic_launcher_background)
            .into(view.app_bar_image_viewer)

        view.app_bar_title_viewer.text = data.Title
        view.plot_viewer.text = data.Plot
        view.actors_viewer.text = data.Actors
        view.director_viewer.text = data.Director
        view.genre_viewer.text = data.Genre
        view.app_bar_rating_viewer.text = data.imdbRating
    }
}