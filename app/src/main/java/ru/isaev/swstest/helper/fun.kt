package ru.isaev.swstest.helper

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.isaev.swstest.R

fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun Fragment.showToast(message: String) {
    Toast.makeText(this.context, message, Toast.LENGTH_SHORT).show()
}

fun Activity.showToast(message: String) {
    Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
}

fun AppCompatActivity.replaceFragment(fragment: Fragment) {
    supportFragmentManager.beginTransaction()
        .addToBackStack(null)
        .replace(
            R.id.fragment_container,
            fragment
        ).commit()
}

fun Fragment.replaceFragment(fragment: Fragment) {
    fragmentManager?.beginTransaction()
        ?.addToBackStack(null)
        ?.replace(
            R.id.fragment_container,
            fragment
        )?.commit()
}

fun getScreenWithIntArg(fragment: Fragment, coffeeId: Int, argKey: String): FragmentScreen {
    val fragment = fragment
    val bundle = Bundle()
    bundle.putInt(argKey, coffeeId)
    fragment.arguments = bundle
    return FragmentScreen { fragment }

}

fun getScreenWithStringArg(fragment: Fragment, token: String, argKey: String): FragmentScreen {
    val mFragment = fragment
    val bundle = Bundle()
    bundle.putString(argKey, token)
    fragment.arguments = bundle
    return FragmentScreen { mFragment }

}

fun getScreen(fragment: Fragment) = FragmentScreen { fragment }

fun Fragment.getIntArg(key: String): Int {
    val bundle = arguments
    return bundle?.getInt(key) ?: 1
}

fun Fragment.getStringArg(key: String?): String {
    val bundle = arguments
    return bundle?.getString(key) ?: "null"
}

