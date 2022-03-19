package ru.isaev.swstest.models

import com.google.gson.annotations.SerializedName

data class CoffeeShopModel (val id: Long, val name: String, val point: LocationItem)

data class LocationItem(val latitude: Long, val longitude: Long)

data class CoffeeShopMenu (val id: Long, val name: String, val imageUrl: String , var price: Int)

data class AuthModelRequest (
    @SerializedName("login") var email: String = "",
    @SerializedName("password") var password: Int = 0
        )

data class ResponseLogin(val token: String, val tokenLiveTime: Int)

