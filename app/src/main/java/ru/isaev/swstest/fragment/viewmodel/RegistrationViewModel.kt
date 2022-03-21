package ru.isaev.swstest.fragment.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.terrakok.cicerone.Router
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import ru.isaev.swstest.data.MainRepository
import ru.isaev.swstest.fragment.CoffeeShopListFragment
import ru.isaev.swstest.helper.getScreen
import ru.isaev.swstest.helper.getScreenWithStringArg
import ru.isaev.swstest.helper.showToast
import ru.isaev.swstest.models.AuthModelRequest
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val repository: MainRepository,
    @ApplicationContext val context: Context,
    val router: Router
) : ViewModel() {

    val user = AuthModelRequest()

    fun sendRegisterRequest(model: AuthModelRequest) {
        viewModelScope.launch {
            if (user.email.isNotEmpty() && user.password != 0 && user.email.contains("@")) {
                val response = repository.registerApp(model)
                    if (response.isSuccessful){
                        val token = response.body()?.token ?: "null"
                        router.navigateTo(getScreenWithStringArg(CoffeeShopListFragment(), token, "Token"))
                }else{
                    Log.e("RegisterError", response.errorBody().toString())
                    }

            } else {
                showToast(context, "Ошибка в данных")
            }
        }
    }

}