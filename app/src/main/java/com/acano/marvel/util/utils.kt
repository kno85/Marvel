package com.acano.marvel.util

import com.acano.marvel.BuildConfig
import com.acano.marvel.domain.Hero
import java.math.BigInteger
import java.security.MessageDigest

fun getHash(ts:String):String{
    val input= ts.plus(BuildConfig.PRIVATE_API_CLIENT_ID).plus(BuildConfig.API_CLIENT_ID)
    return md5(input)
}
private fun md5(input:String): String {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
}
 fun getMockedHeroList(): List<Hero> {
    val list= ArrayList<Hero>()
    for(i in 1..10){
        list.add(
            Hero(i,"Hero "+i,
                "bla bla bla,bla bla bla,bla bla bla,bla bla bla,bla bla bla,bla bla bla",
                "")
        )
    }
    return list
}