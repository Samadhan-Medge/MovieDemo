package com.samadhan.movieapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.samadhan.movieapp.R
import com.samadhan.movieapp.databinding.RowMovieItemBinding
import com.samadhan.movieapp.models.Movie
import java.util.*

/**
 * Adapter class to show Movies in list/grid
 */
class MovieListAdapter(
    private val mContext: Context,
    private val mValues: ArrayList<Movie>,
    private val mListener: OnMovieSelectionListener?
) : androidx.recyclerview.widget.RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    inner class ViewHolder constructor(rowMovieItemBinding: RowMovieItemBinding) :
        androidx.recyclerview.widget.RecyclerView.ViewHolder(rowMovieItemBinding.root),
        View.OnClickListener {

        lateinit var movie: Movie
        var rowMovieItemBinding: RowMovieItemBinding

        init {
            rowMovieItemBinding.root.setOnClickListener(this)
            this.rowMovieItemBinding = rowMovieItemBinding
        }

        /**
         * Set Movie instance for row item
         */
        fun setMovieData(movie: Movie) {
            this.movie = movie
        }

        /**
         * on Click method for Row item
         */
        override fun onClick(view: View) {
            mListener?.onMovieSelected(movie)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(mContext),
                R.layout.row_movie_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.rowMovieItemBinding.movie = mValues[position]
        viewHolder.setMovieData(mValues[position])
    }

    override fun getItemCount(): Int {
        return mValues.size
    }

    /**
     * Callback interface to send event on Movie item selected
     */
    interface OnMovieSelectionListener {
        fun onMovieSelected(movie: Movie)
    }
}