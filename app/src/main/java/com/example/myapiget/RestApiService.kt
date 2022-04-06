package com.example.myapiget

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RestApiService {

    fun addUser(userData: ResponseData, onResult: (ResponseData?) -> Unit){
        val retrofit = ServiceBuilder.buildService(APIService::class.java)
        retrofit.update(userData).enqueue(
            object : Callback<ResponseData> {
                override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse(call: Call<ResponseData>, response: Response<ResponseData>) {
                    val addedUser = response.body()
                    onResult(addedUser)
                }
            }
        )
    }
}