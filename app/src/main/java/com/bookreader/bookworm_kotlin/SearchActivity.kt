package com.bookreader.bookworm_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        //reference to the widgets
        val search_button = findViewById<Button>(R.id.search_button)
        val search_bar = findViewById<TextView>(R.id.search_bar)
        val BASE_URL = "https://www.googleapis.com/books/v1/"
        val KEY = "AIzaSyBv3n8fr20-ayiAeuo9KDSklH3gsI0HEec"

        //set on_click to the button
        search_button.setOnClickListener {

            Toast.makeText(this@SearchActivity, search_bar.text, Toast.LENGTH_SHORT).show()
            getBookData(search_bar.text.toString(), BASE_URL, KEY)
        }
    }

    // gets the search bar data and searches for a result with this name
    private fun getBookData(book: String, url: String, key: String) {

        // builds the basic URL
        val retrofit_builder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(url)
            .build()
            .create(APIInterface::class.java)

        // adds the endpoint with the search components and the API key
        val retrofit_volume_data = retrofit_builder.getVolumeData(book, key)
        val retrofit_book_data = retrofit_builder.getBookData(book, key)


        // calls the API
        //auto generated ctrl + shift + space
        retrofit_volume_data.enqueue(object : Callback<VolumeInfo?> {
            override fun onResponse(call: Call<VolumeInfo?>, response: Response<VolumeInfo?>) {
                // test purposes only: shows the HTTP code the API returns
                Toast.makeText(this@SearchActivity, response.code().toString(), Toast.LENGTH_SHORT).show()
                TODO("Show data on the screen")
            }

            override fun onFailure(call: Call<VolumeInfo?>, t: Throwable) {
                // logs on fail
                Log.d("SearchActivity", "OnFailure" + t.message)
            }
        })

        retrofit_book_data.enqueue(object : Callback<BookData?> {
            override fun onResponse(call: Call<BookData?>, response: Response<BookData?>) {
                // test purposes only: shows the HTTP code the API returns
                Toast.makeText(this@SearchActivity, response.code().toString(), Toast.LENGTH_SHORT).show()
                TODO("Send data to table books")
            }

            override fun onFailure(call: Call<BookData?>, t: Throwable) {
                // logs on fail
                Log.d("SearchActivity", "OnFailure" + t.message)
            }
        })

    }
}