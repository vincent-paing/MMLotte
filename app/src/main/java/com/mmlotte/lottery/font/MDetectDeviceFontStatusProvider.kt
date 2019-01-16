package com.mmlotte.lottery.font

import android.content.Context
import android.content.SharedPreferences
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.edit
import com.mmlotte.lottery.domain.DeviceFontStatusProvider
import com.mmlotte.lottery.domain.DeviceFontStatusProvider.FontStatus
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Vincent on 12/14/18
 */
@Singleton
class MDetectDeviceFontStatusProvider @Inject constructor(
  context: Context,
  sharedPreferences: SharedPreferences
) : DeviceFontStatusProvider {

  companion object {
    private const val KEY_FONT = "FONT"
    private const val CODE_ZAWGYI = 1
    private const val CODE_UNICODE = 2
  }

  private val isUnicode: Boolean

  init {
    var preferenceValue = sharedPreferences.getInt(KEY_FONT, -1)

    if (preferenceValue == -1) {

      val textView = TextView(context, null)
      textView.layoutParams = ViewGroup.LayoutParams(
        ViewGroup.LayoutParams.WRAP_CONTENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
      )

      textView.text = "\u1000"
      textView.measure(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
      val length1 = textView.measuredWidth

      textView.text = "\u1000\u1039\u1000"
      textView.measure(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
      val length2 = textView.measuredWidth

      val isUnicode = length1 == length2

      Timber.i("Device font is unicode : $isUnicode")

      sharedPreferences.edit {
        putInt(KEY_FONT, if (isUnicode) CODE_UNICODE else CODE_ZAWGYI)
      }

    }

    preferenceValue = sharedPreferences.getInt(KEY_FONT, -1)

    isUnicode = when (preferenceValue) {
      CODE_ZAWGYI -> {
        false
      }
      CODE_UNICODE -> {
        true
      }
      else -> {
        Timber.i("Can't detect font, defaulting to unicode")
        true
      }
    }
  }

  override fun getFontStatus(): FontStatus {
    return if (isUnicode) {
      FontStatus.UNCIODE
    } else {
      FontStatus.ZAWGYI
    }
  }
}

