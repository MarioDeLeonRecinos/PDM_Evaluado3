package com.echavez.labo3evaluado.activities

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.echavez.labo3evaluado.AppConstants
import com.echavez.labo3evaluado.Entities.Movie
import com.echavez.labo3evaluado.R
import com.echavez.labo3evaluado.fragments.MovieListFragment
import com.echavez.labo3evaluado.fragments.MovieViewerFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MovieListFragment.ClickedMovieListener {

    private lateinit var mainFragment: MovieListFragment
    private lateinit var mainContentFragment: MovieViewerFragment
    private var resource = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initFragments()
    }

    fun initFragments(){
        mainFragment = MovieListFragment()

        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT){
            resource = R.id.portrait_main_place_holder
        }
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
            showContent(R.id.land_main_movieviewer_ph, Movie())
            resource =R.id.land_main_place_holder
        }

        val intent = Intent(this, NewMovieActivity::class.java)
        main_add_button.setOnClickListener { startActivityForResult(intent , AppConstants.ADD_TASK_REQUEST) }

        changeFragment(resource, mainFragment)
    }

    private fun changeFragment(id: Int, frag: Fragment){ supportFragmentManager.beginTransaction().replace(id, frag).commit() }

    private fun showContent(id_placeholder: Int, movie: Movie) {
        mainContentFragment = MovieViewerFragment.newInstance(movie)
        changeFragment(id_placeholder, mainContentFragment)
    }

    override fun managePortraitItemClick(movie: Movie) = showContent(R.id.portrait_main_place_holder, movie)

    override fun managedLandscapeItemClick(movie: Movie) = showContent(R.id.land_main_movieviewer_ph, movie)

}
