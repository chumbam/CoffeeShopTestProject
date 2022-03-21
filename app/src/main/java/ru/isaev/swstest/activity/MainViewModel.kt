package ru.isaev.swstest.activity

import androidx.lifecycle.ViewModel
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(router: Router, navigatorHolder: NavigatorHolder ): ViewModel(){
    val navHolder = navigatorHolder
}