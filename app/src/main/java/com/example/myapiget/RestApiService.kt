package com.example.myapiget

import com.cursokotlin.retrofitkotlinexample.APIService
import com.cursokotlin.retrofitkotlinexample.DogsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RestApiService {
    fun addUser(userData: DogsResponse, onResult: (DogsResponse?) -> Unit){
        val retrofit = ServiceBuilder.buildService(APIService::class.java)
        retrofit.addUser(userData).enqueue(
            object : Callback<DogsResponse> {
                override fun onFailure(call: Call<DogsResponse>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse( call: Call<DogsResponse>, response: Response<DogsResponse>) {
                    val addedUser = response.body()
                    onResult(addedUser)
                }
            }
        )
    }
}