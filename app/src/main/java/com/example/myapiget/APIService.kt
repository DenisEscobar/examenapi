package com.cursokotlin.retrofitkotlinexample

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface APIService {
    @GET("post")
    fun find():Call<MutableList<DogsResponse>>
    @GET("post")
    fun findnom(@Query("nombre") text: String?):Call<MutableList<DogsResponse>>
    @GET("post")
    suspend fun getapi(): Response<ResponseBody>
    @GET("/post/?id=3")
    suspend fun getapiall(): Response<ResponseBody>

    @Headers("Content-Type: application/json")
    @POST("post")
    fun addUser(@Body userData: DogsResponse): Call<DogsResponse>

    @DELETE("/post/{id}")
    fun delete(@Path("id") text: String?):Call<DogsResponse>
}