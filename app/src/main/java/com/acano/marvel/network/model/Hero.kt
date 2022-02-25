package com.acano.marvel.network.model

import com.google.gson.annotations.SerializedName

data class Hero(
    @SerializedName("id")
    var id:Int?,
    @SerializedName("name")
    var name:String?,
    @SerializedName("description")
    var description:String?,
    @SerializedName("thumbnail")
    var thumbnail:Thumbnail?
)




