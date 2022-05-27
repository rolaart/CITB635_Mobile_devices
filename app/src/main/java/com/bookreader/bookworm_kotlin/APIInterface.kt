package com.bookreader.bookworm_kotlin

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIInterface {

    @GET(value = "volumes")
    fun getVolumeData(@Query("q") book: String, @Query("key") api_key: String): Call<VolumeInfo>

    @GET(value = "volumes")
    fun getBookData(@Query("q") book: String, @Query("key") api_key: String): Call<BookData>


}