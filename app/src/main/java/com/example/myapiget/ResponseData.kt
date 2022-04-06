package com.example.myapiget

import com.google.gson.annotations.SerializedName

data class ResponseData (
    @SerializedName("id") var id: Int?,
    @SerializedName("nom") var nom: String,
    )