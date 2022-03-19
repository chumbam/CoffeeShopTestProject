package ru.isaev.swstest.data

import ru.isaev.swstest.api.AuthApiService
import ru.isaev.swstest.api.CoffeeShopApiService
import ru.isaev.swstest.models.AuthModelRequest
import javax.inject.Inject

class MainRemoteData @Inject constructor(
    private val loginApiService: AuthApiService,
    private val coffeeShopApiService: CoffeeShopApiService
) {

    suspend fun loginApp(model: AuthModelRequest) = loginApiService.loginAppRequest(model)
    suspend fun registerApp(model: AuthModelRequest) = loginApiService.registerAppRequest(model)

    suspend fun getCoffeeShopList() = coffeeShopApiService.fetchCoffeeShopList()
    suspend fun getMenuList(shopId: Int) = coffeeShopApiService.fetchCoffeeShopMenuList(shopId)
}