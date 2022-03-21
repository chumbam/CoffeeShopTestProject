package ru.isaev.swstest.data

import ru.isaev.swstest.models.AuthModelRequest
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MainRepository @Inject constructor(private val remoteData: MainRemoteData) {

    suspend fun loginApp(model: AuthModelRequest) = remoteData.loginApp(model)
    suspend fun registerApp(model: AuthModelRequest) = remoteData.registerApp(model)

    suspend fun getCoffeeShopList() = remoteData.getCoffeeShopList()
    suspend fun getMenuList(shopId: String) = remoteData.getMenuList(shopId)

}