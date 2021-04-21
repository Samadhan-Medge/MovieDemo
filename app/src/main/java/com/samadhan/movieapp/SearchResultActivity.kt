package com.samadhan.movieapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.samadhan.movieapp.adapters.MovieListAdapter
import com.samadhan.movieapp.databinding.ActivitySearchResultBinding
import com.samadhan.movieapp.enums.APIState
import com.samadhan.movieapp.models.Movie
import com.samadhan.movieapp.models.MovieSearchResult
import com.samadhan.movieapp.service.SearchMovieListService
import com.samadhan.movieapp.service.ServiceBuilder
import com.samadhan.movieapp.utils.AppConstants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.set

class SearchResultActivity : AppCompatActivity() {
    // Binding instance for Search Result View
    lateinit var viewBinding: ActivitySearchResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_search_result)
        viewBinding.searchText = intent.getStringExtra(AppConstants.SEARCH_TITLE).toString()
        getData()
    }

    /**
     * Method to perform click actions for view
     */
    fun performOnClick(view: View) {
        when (view.id) {
            R.id.btnRetry -> getData() // Retry button click for fetching data again
            R.id.ivBackButton -> onBackPressed()// Back button click handling events
        }
    }

    /**
     * Method to fetch list of movies from API
     */
    private fun getData() {
        // Set state as loading to show progress bar
        viewBinding.apiState = APIState.LOADING
        val request = ServiceBuilder.buildService(SearchMovieListService::class.java)
        // HashMap for APU Request Parameters
        val data: MutableMap<String, String> = HashMap()
        data["s"] = viewBinding.searchText.toString() // Text to be search from API
        data[AppConstants.KEY_API] = BuildConfig.API_KEY// API Key
        val call = request.getSearchResult(data)
        call!!.enqueue(object : Callback<MovieSearchResult?>,
            MovieListAdapter.OnMovieSelectionListener {
            override fun onResponse(
                call: Call<MovieSearchResult?>,
                response: Response<MovieSearchResult?>
            ) {
                // Check if Response Success or not
                if (response.isSuccessful) {
                    // Set state as Success to show result
                    viewBinding.apiState = APIState.SUCCESS
                    viewBinding.searchResultAdapter = MovieListAdapter(
                        this@SearchResultActivity,
                        response.body()!!.Search as ArrayList<Movie>, this
                    )
                } else
                    // Set state as Failure to show retry screen
                    viewBinding.apiState = APIState.FAILURE
            }

            override fun onFailure(call: Call<MovieSearchResult?>, t: Throwable) {
                // Set state as Failure to show retry screen
                viewBinding.apiState = APIState.FAILURE
            }

            /**
             * Callback method of GridView Movie item selection
             */
            override fun onMovieSelected(movie: Movie) {
                // Redirect to details screen with id
                val intent = Intent(this@SearchResultActivity, MovieDetailsActivity::class.java)
                intent.putExtra(AppConstants.IMDB_ID, movie.imdbID)
                startActivity(intent)
            }
        })
    }
}