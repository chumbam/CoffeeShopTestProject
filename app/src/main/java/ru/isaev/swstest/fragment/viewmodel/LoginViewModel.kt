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
import ru.isaev.swstest.helper.getScreenWithStringArg
import ru.isaev.swstest.helper.showToast
import ru.isaev.swstest.models.AuthModelRequest
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    @ApplicationContext val context: Context,
    private val repository: MainRepository,
    private val router: Router
) : ViewModel() {

    val user = AuthModelRequest()

    fun sendLoginRequest(model: AuthModelRequest) {
        viewModelScope.launch {
            if (user.email.contains("@") && user.password != 0  ) {
                val response = repository.loginApp(model)
                if (response.isSuccessful){
                    val token = response.body()?.token ?: "null"
                    router.navigateTo(getScreenWithStringArg(CoffeeShopListFragment(), token, "Token"))
                }else{
                    Log.e("LoginError", response.body().toString())
                }
            } else {
                showToast(context, "Неверный формат полей")
            }
        }
    }


}