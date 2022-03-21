package ru.isaev.swstest.fragment.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.terrakok.cicerone.Router
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import okhttp3.internal.wait
import retrofit2.Response
import ru.isaev.swstest.data.MainRepository
import ru.isaev.swstest.fragment.PaymentFragment
import ru.isaev.swstest.helper.getScreen
import ru.isaev.swstest.helper.showToast
import ru.isaev.swstest.models.CoffeeShopMenu
import ru.isaev.swstest.models.CoffeeShopModel
import javax.inject.Inject

@HiltViewModel
class MenuListViewModel @Inject constructor(
    val repository: MainRepository,
    @ApplicationContext val context: Context,
    val router: Router
) : ViewModel() {

    val menuListItem = MutableLiveData<List<CoffeeShopMenu>>()
    val menuPositionCount: MutableMap<String,Int> = mutableMapOf()


    fun fetchMenuListRequest(shopId: String){
        viewModelScope.launch {
            val response = repository.getMenuList(shopId)
            if(response.isSuccessful){
              getListData(response)
            } else{
                showToast(context, response.errorBody().toString())
            }
        }
    }
    fun navigateToPaymentFragment(){
        router.navigateTo(getScreen(PaymentFragment()))
    }

    private fun getListData(response: Response<List<CoffeeShopMenu>>) {
        menuListItem.value = response.body()
    }

}