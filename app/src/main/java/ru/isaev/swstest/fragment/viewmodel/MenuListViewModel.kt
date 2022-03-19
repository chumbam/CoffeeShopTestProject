package ru.isaev.swstest.fragment.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import ru.isaev.swstest.data.MainRepository
import ru.isaev.swstest.helper.showToast
import ru.isaev.swstest.models.CoffeeShopMenu
import javax.inject.Inject

@HiltViewModel
class MenuListViewModel @Inject constructor(
    val repository: MainRepository,
    @ApplicationContext val context: Context
) : ViewModel() {

 val menuListItem = MutableLiveData<List<CoffeeShopMenu>>()

    fun fetchMenuListRequest(shopId: Int){
        viewModelScope.launch {
            val response = repository.getMenuList(shopId)
            if(response.isSuccessful){
              menuListItem.value = response.body()
            } else{
                showToast(context, response.errorBody().toString())
            }
        }
    }

}