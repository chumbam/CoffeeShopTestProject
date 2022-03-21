package ru.isaev.swstest.activity

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.androidx.AppNavigator
import dagger.hilt.android.AndroidEntryPoint
import ru.isaev.swstest.R
import ru.isaev.swstest.fragment.LoginFragment
import ru.isaev.swstest.fragment.RegisterFragment


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val navigator: Navigator = AppNavigator(this, R.id.fragment_container)
    private val mViewModel by viewModels<MainViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val prefs: SharedPreferences = getSharedPreferences("first_launch", MODE_PRIVATE)
        if (prefs.getBoolean("first_launch", true)){
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, RegisterFragment())
                .commit()
            prefs.edit().putBoolean("first_launch", false).commit();
        } else{
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, LoginFragment())
                .commit()
        }


    }

    override fun onResume() {
        super.onResume()
        mViewModel.navHolder.setNavigator(navigator)
    }

    override fun onPause() {
        mViewModel.navHolder.removeNavigator()
        super.onPause()
    }

}