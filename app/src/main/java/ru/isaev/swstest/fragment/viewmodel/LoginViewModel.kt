package ru.isaev.swstest.fragment.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import ru.isaev.swstest.data.MainRepository
import ru.isaev.swstest.helper.showToast
import ru.isaev.swstest.models.AuthModelRequest
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    @ApplicationContext val context: Context,
    private val repository: MainRepository
) : ViewModel() {

    val user = AuthModelRequest()

    fun sendLoginRequest(model: AuthModelRequest) {
        viewModelScope.launch {
            if (user.email.isNotEmpty() && user.password != 0 && user.email.contains("@")) {
                val response = repository.loginApp(model)
            } else {
                showToast(context, "Неверный формат полей")
            }
        }
    }


}