package ru.isaev.swstest.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import ru.isaev.swstest.models.CoffeeShopMenu
import ru.isaev.swstest.models.CoffeeShopModel

interface CoffeeShopApiService {

    @GET("./locations")
    suspend fun fetchCoffeeShopList(): Response<List<CoffeeShopModel>>

    @GET("./location/{itemId}/menu")
    suspend fun fetchCoffeeShopMenuList(@Path("itemId") userId: Int): Response<List<CoffeeShopMenu>>
}
