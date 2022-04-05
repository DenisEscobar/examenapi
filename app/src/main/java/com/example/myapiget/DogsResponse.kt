package com.cursokotlin.retrofitkotlinexample

import com.google.gson.annotations.SerializedName

data class DogsResponse (
    @SerializedName("id") var id: Int?,
    @SerializedName("nombre") var nombre: String,
    )