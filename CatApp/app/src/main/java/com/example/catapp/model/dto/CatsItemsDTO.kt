package com.example.catapp.model.dto

import com.google.gson.annotations.SerializedName

data class CatsItemsDTO(
    @SerializedName("id") val id: String,
    @SerializedName("url") val url: String
)