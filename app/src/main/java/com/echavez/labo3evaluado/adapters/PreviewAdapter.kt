package com.echavez.labo3evaluado.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.echavez.labo3evaluado.Entities.Movie
import com.echavez.labo3evaluado.Entities.MoviePreview
import com.echavez.labo3evaluado.R
import kotlinx.android.synthetic.main.cardview_preview.view.*

class PreviewAdapter(var movies: List<MoviePreview>, val clickListener: (MoviePreview, View) -> Unit ):RecyclerView.Adapter<PreviewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_preview, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(movies[position] , clickListener)

    fun changeDataSet(newMovieList: List<MoviePreview>) {
        movies = newMovieList
        notifyDataSetChanged()
    }

    class ViewHolder(item: View): RecyclerView.ViewHolder(item){
        fun bind(movie: MoviePreview, clickListener: (MoviePreview, View) -> Unit) = with(itemView){
            Glide.with(itemView.context)
                .load(movie.Poster)
                .placeholder(R.drawable.ic_launcher_background)
                .into(preview_image_cv)
            preview_title_cv.text = movie.Title
            preview_year_cv.text = movie.Year
            if (movie.selected) preview_selected_cv.visibility = View.VISIBLE else preview_selected_cv.visibility = View.GONE
            this.setOnClickListener { clickListener(movie, preview_selected_cv) }
        }
    }
}