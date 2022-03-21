package ru.isaev.swstest.fragment.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.terrakok.cicerone.Router
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import retrofit2.Response
import ru.isaev.swstest.data.MainRepository
import ru.isaev.swstest.fragment.MapsFragment
import ru.isaev.swstest.fragment.MenuListFragment
import ru.isaev.swstest.helper.getScreen
import ru.isaev.swstest.helper.getScreenWithIntArg
import ru.isaev.swstest.helper.getScreenWithStringArg
import ru.isaev.swstest.helper.showToast
import ru.isaev.swstest.models.CoffeeShopModel
import javax.inject.Inject

@HiltViewModel
class CoffeeShopListViewModel @Inject constructor(
    private val repository: MainRepository,
    @ApplicationContext val context: Context,
    private val router: Router
) : ViewModel() {

    val coffeeShopListItem = MutableLiveData<List<CoffeeShopModel>>()

    fun fetchCoffeeShopListRequest() {
        viewModelScope.launch {
            val response = repository.getCoffeeShopList()
            if (response.isSuccessful) {
                getListData(response)
            } else {
                showToast(context, response.errorBody().toString())
            }
        }
    }

    fun navigateToMenuFragment(id: String){
        router.navigateTo(getScreenWithStringArg(MenuListFragment(),id,"ID"))
    }
    fun navigateToMapsFragment(){
        router.navigateTo(getScreen(MapsFragment()))
    }

    private fun getListData(response: Response<List<CoffeeShopModel>>) {
        coffeeShopListItem.value = response.body()
    }

}