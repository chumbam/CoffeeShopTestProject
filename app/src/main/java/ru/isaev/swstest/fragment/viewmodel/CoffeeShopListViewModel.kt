package ru.isaev.swstest.fragment.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import retrofit2.Response
import ru.isaev.swstest.data.MainRepository
import ru.isaev.swstest.helper.showToast
import ru.isaev.swstest.models.CoffeeShopModel
import javax.inject.Inject

@HiltViewModel
class CoffeeShopListViewModel @Inject constructor(
    private val repository: MainRepository,
    @ApplicationContext val context: Context
) : ViewModel() {

    val coffeeShopListItem = MutableLiveData<List<CoffeeShopModel>>()

    fun fetchCoffeeShopListRequest() {
        viewModelScope.launch {
            val response = repository.getCoffeeShopList()
            if(response.isSuccessful){
                getListData(response)
            }else{
                showToast(context, response.errorBody().toString())
            }
        }
    }

    private fun getListData(response: Response<List<CoffeeShopModel>>) {
        coffeeShopListItem.value = response.body()

    }

}