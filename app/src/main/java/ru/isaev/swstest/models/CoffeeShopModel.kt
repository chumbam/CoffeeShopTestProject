package ru.isaev.swstest.models

import com.google.gson.annotations.SerializedName

data class CoffeeShopModel(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("point") val point: LocationItem
)

data class LocationItem(
    @SerializedName("latitude") val latitude: Double,
    @SerializedName("longitude") val longitude: Double
)

data class CoffeeShopMenu(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("imageURL") val imageURL: String,
    @SerializedName("price") var price: Int,
    var count: Int = 0
)

data class AuthModelRequest(
    @SerializedName("login") var email: String = "",
    @SerializedName("password") var password: Int = 0
)
data class PaymentDataModel(val name: String, var count: Int)

data class ResponseLogin(val token: String, val tokenLiveTime: Int)

