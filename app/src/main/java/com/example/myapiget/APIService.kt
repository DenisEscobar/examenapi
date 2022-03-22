package com.cursokotlin.retrofitkotlinexample

import retrofit2.Call
import retrofit2.http.*

interface APIService {
    @GET
    fun getCharacterByName(@Url url:String): Call<DogsResponse>

    @Headers("Content-Type: application/json")
    @POST("users")
    fun addUser(@Body userData: DogsResponse): Call<DogsResponse>
}