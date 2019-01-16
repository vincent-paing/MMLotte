package com.mmlotte.lottery

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.mmlotte.lottery.domain.DeviceFontStatusProvider
import com.mmlotte.lottery.domain.DeviceFontStatusProvider.FontStatus
import com.mmlotte.lottery.font.FontUtils
import com.mmlotte.lottery.view.MainActivity
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 * Created by Vincent on 12/14/18
 */
class SplashActivity : AppCompatActivity(), HasSupportFragmentInjector {

  @Inject
  lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

  @Inject lateinit var deviceFontStatusProvider: DeviceFontStatusProvider

  @Inject lateinit var fontUtils: FontUtils

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    //Determine font status
    if (deviceFontStatusProvider.getFontStatus() == FontStatus.ZAWGYI) {
      fontUtils.updateToZawgyiLocale(this)
    }

    val intent = Intent(this, MainActivity::class.java)
    startActivity(intent)
    finish()
  }

  override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingAndroidInjector

}