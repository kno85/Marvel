package com.acano.marvel.network.model

import com.google.gson.annotations.SerializedName

data class Thumbnail(
    @SerializedName("path")
    var path:String?="",
    @SerializedName("extension")
    var extension:String?=""
)




