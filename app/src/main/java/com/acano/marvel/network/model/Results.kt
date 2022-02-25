package com.acano.marvel.network.model

import com.google.gson.annotations.SerializedName

data class Results(
        @SerializedName("results")
         var items:List<Hero>?
)