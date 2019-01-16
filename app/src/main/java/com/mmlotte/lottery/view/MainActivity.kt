package com.mmlotte.lottery.view

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.aungkyawpaing.consentdialog.AdMobConsentDialog
import com.aungkyawpaing.consentdialog.CrashLogConsentDialog
import com.crashlytics.android.Crashlytics
import com.crashlytics.android.core.CrashlyticsCore
import com.google.ads.consent.ConsentInformation
import com.google.ads.consent.ConsentStatus
import com.google.ads.consent.ConsentStatus.NON_PERSONALIZED
import com.google.firebase.messaging.FirebaseMessaging
import com.mmlotte.app.base.core.mvp.MvpActivity
import com.mmlotte.lottery.BuildConfig
import com.mmlotte.lottery.R.id
import com.mmlotte.lottery.R.layout
import com.mmlotte.lottery.helper.FcmConstants
import com.mmlotte.lottery.helper.NotificationChannelHelper
import com.mmlotte.lottery.viewmodel.MainViewModel
import io.fabric.sdk.android.Fabric
import kotlinx.android.synthetic.main.activity_main.bottomNavigationView
import timber.log.Timber

class MainActivity : MvpActivity<MainView, MainViewModel>(), MainView {
  override val viewModel: MainViewModel by contractedViewModels()

  override val layoutResId: Int
    get() = layout.activity_main

  private val navigationController by lazy {
    findNavController(id.navHostFragment)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    bottomNavigationView.setupWithNavController(navigationController)

    if (savedInstanceState == null) {
      viewModel.checkForConsents()
    }

    NotificationChannelHelper(this).initChannels()
    setUpFirebaseCloudMessaging()
  }

  private fun setUpFirebaseCloudMessaging() {
    FirebaseMessaging.getInstance().subscribeToTopic(FcmConstants.TOPIC_NEW_RESULT)
      .addOnCompleteListener {
        if (it.isSuccessful) {
          Timber.i("Topic subscriptiion successful")
        }
      }
      .addOnFailureListener {
        Timber.e(it)
      }
  }

  override fun onSupportNavigateUp(): Boolean =
    navigationController.navigateUp() || super.onSupportNavigateUp()

  //region
  override fun showConsentDialogForAd() {
    AdMobConsentDialog.newInstance(object : AdMobConsentDialog.ConsentListener {
      override fun onConsent(allowPersonalized: Boolean) {
        viewModel.updateConsentStatusForAd(allowPersonalized)
      }
    }, "").show(supportFragmentManager, "AD_CONSENT")
  }

  override fun showConsentDialogForCrashLog() {
    CrashLogConsentDialog.newInstance(object : CrashLogConsentDialog.ConsentListener {
      override fun onConsent(hasConsented: Boolean) {
        viewModel.updateConsentStatusForCrashLog(hasConsented)
      }
    }, "").show(supportFragmentManager, "CRASH_LOG_CONSENT")
  }

  override fun setUpCrashLogging() {
    val crashlyticsKit =
      Crashlytics.Builder().core(CrashlyticsCore.Builder().disabled(BuildConfig.DEBUG).build())
        .build()
    Fabric.with(this, crashlyticsKit)
  }

  override fun setUpAd(allowPersonalizedAd: Boolean) {
    val consentStatus = if (allowPersonalizedAd) ConsentStatus.PERSONALIZED else NON_PERSONALIZED
    ConsentInformation.getInstance(this).consentStatus = consentStatus
  }
  //endregion

}
