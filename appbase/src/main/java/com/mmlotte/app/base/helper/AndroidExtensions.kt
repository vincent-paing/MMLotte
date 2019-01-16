package com.mmlotte.app.base.helper

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.app.ComponentActivity
import androidx.fragment.app.Fragment

/**
 * Created by Vincent on 12/6/18
 */
fun View.setVisible(isVisible: Boolean) {
  if (isVisible) {
    this.visibility = View.VISIBLE
  } else {
    this.visibility = View.GONE
  }
}

fun Array<View>.setVisible(isVisible: Boolean) {

  val visibility = if (isVisible) {
    android.view.View.VISIBLE
  } else {
    android.view.View.GONE
  }

  this.forEach {
    it.visibility = visibility
  }
}

fun ViewGroup.inflater(): LayoutInflater {
  return LayoutInflater.from(this.context)
}

fun ComponentActivity.showShortToast(message: CharSequence) {
  Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
}

fun ComponentActivity.showShortToast(@StringRes resId: Int) {
  Toast.makeText(applicationContext, resId, Toast.LENGTH_SHORT).show()
}

fun ComponentActivity.showLongToast(message: CharSequence) {
  Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
}

fun ComponentActivity.showLongToast(@StringRes resId: Int) {
  Toast.makeText(applicationContext, resId, Toast.LENGTH_LONG).show()
}

fun Fragment.showShortToast(message: CharSequence) {
  Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun Fragment.showShortToast(@StringRes resId: Int) {
  Toast.makeText(context, resId, Toast.LENGTH_SHORT).show()
}

fun Fragment.showLongToast(message: CharSequence) {
  Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}

fun Fragment.showLongToast(@StringRes resId: Int) {
  Toast.makeText(context, resId, Toast.LENGTH_LONG).show()
}
