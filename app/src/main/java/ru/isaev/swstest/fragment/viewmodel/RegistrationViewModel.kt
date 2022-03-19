package ru.isaev.swstest.fragment.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import ru.isaev.swstest.data.MainRepository
import ru.isaev.swstest.helper.showToast
import ru.isaev.swstest.models.AuthModelRequest
import ru.isaev.swstest.models.ResponseLogin
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val repository: MainRepository,
    @ApplicationContext val context: Context
) : ViewModel() {

    val user = AuthModelRequest()

    fun sendRegisterRequest(model: AuthModelRequest) {
        viewModelScope.launch {
            if (user.email.isNotEmpty() && user.password != 0 && user.email.contains("@")) {
                val response = repository.registerApp(model)
                    if (response.isSuccessful){
                        val token = response.body()?.token
                        Log.e("ResponseTag", "$token")
                }

            } else {
                showToast(context, "Ошибка в данных")
            }
        }
    }

}