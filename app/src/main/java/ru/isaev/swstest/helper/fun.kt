package ru.isaev.swstest.helper

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
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
            fragment)?.commit()
}