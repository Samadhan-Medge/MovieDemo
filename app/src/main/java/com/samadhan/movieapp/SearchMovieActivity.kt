package com.samadhan.movieapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import com.samadhan.movieapp.utils.AppConstants

class SearchMovieActivity : AppCompatActivity() {
    // EditText instance for Search Field
    private lateinit var etSearchMovie: AppCompatEditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_movie)
        etSearchMovie = findViewById(R.id.etSearchMovie);
    }

    /**
     * Method to perform click actions for view
     */
    fun performSearchClick(view: View) {
        val strSearchText: String = etSearchMovie.editableText.toString()
        if (strSearchText.isNotEmpty()) {
            // Redirect to Result Screen with entered text
            val intent = Intent(this@SearchMovieActivity, SearchResultActivity::class.java)
            intent.putExtra(AppConstants.SEARCH_TITLE, strSearchText)
            startActivity(intent)
        } else {
            // Show Toast message if user try to search without entering text
            Toast.makeText(this, getString(R.string.error_message_search_movie), Toast.LENGTH_SHORT)
                .show()
        }
    }
}