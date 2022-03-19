package ru.isaev.swstest.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import ru.isaev.swstest.models.AuthModelRequest
import ru.isaev.swstest.models.ResponseLogin

interface AuthApiService {

    @POST("./auth/login")
    suspend fun loginAppRequest(@Body modelRequest: AuthModelRequest): Response<ResponseLogin>

    @POST("./auth/register")
    suspend fun registerAppRequest(@Body modelRequest: AuthModelRequest): Response<ResponseLogin>
}