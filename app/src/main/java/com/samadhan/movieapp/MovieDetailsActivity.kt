package com.samadhan.movieapp


import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.samadhan.movieapp.databinding.ActivityMovieDetailsBinding
import com.samadhan.movieapp.enums.APIState
import com.samadhan.movieapp.models.Movie
import com.samadhan.movieapp.service.GetMovieDetailsService
import com.samadhan.movieapp.service.ServiceBuilder
import com.samadhan.movieapp.utils.AppConstants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailsActivity : AppCompatActivity() {
    // Binding instance for Search Result Details View
    lateinit var viewBinding: ActivityMovieDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details)
        getData()
    }

    /**
     * Method to perform click actions for view
     */
    fun performOnClick(view: View) {
        when (view.id) {
            R.id.btnRetry -> getData() // Retry button click to fetch data
            R.id.ivBackButton -> onBackPressed()// Back button click to redirect to back screen
        }
    }

    /**
     * Method to fetch list of movies from API
     */
    private fun getData() {
        // Set state as loading to show progress bar
        viewBinding.apiState = APIState.LOADING
        val request = ServiceBuilder.buildService(GetMovieDetailsService::class.java)
        val data: MutableMap<String, String> = HashMap()
        data["i"] = intent.getStringExtra(AppConstants.IMDB_ID).toString()// pass id to fetch movie details
        data[AppConstants.KEY_API] = BuildConfig.API_KEY // API Key
        val call = request.getMovieDetails(data)
        call!!.enqueue(object : Callback<Movie?> {
            override fun onResponse(
                call: Call<Movie?>,
                response: Response<Movie?>
            ) {
                if (response.isSuccessful) {
                    // Set state as success to show result data on UI
                    viewBinding.apiState = APIState.SUCCESS
                    viewBinding.movie = response.body()
                } else
                // Set state as failure to show retry UI
                    viewBinding.apiState = APIState.FAILURE
            }

            override fun onFailure(call: Call<Movie?>, t: Throwable) {
                // Set state as failure to show retry UI
                viewBinding.apiState = APIState.FAILURE
            }
        })
    }
}