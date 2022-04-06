package com.example.myapiget

import retrofit2.Call
import retrofit2.http.*

interface APIService {

    @GET("cicles")
    fun findid(@Query("id") text: Int?):Call<MutableList<ResponseData>>

    @DELETE("/alumnes/{id}")
    fun deleteid(@Path("id") text: Int?):Call<ResponseData>

    @GET("/qualificacions?modul=1&(nota>=5&nota<=10)")
    fun getapro():Call<MutableList<ResponseData>>
    @GET("/qualificacions?modul=1&(nota=5||nota=6||nota=7||nota=8||nota=9||nota=10)")
    fun getapro2():Call<MutableList<ResponseData>>

    @PUT("/alumnes/2")
    fun update(@Body requestBody: ResponseData): Call<ResponseData>
}