package com.acano.marvel.network.model

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("data")
    var results: Results
)