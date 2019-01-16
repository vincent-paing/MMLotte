package com.mmlotte.lottery

import android.app.Activity
import androidx.multidex.MultiDexApplication
import com.google.android.gms.ads.MobileAds
import com.google.firebase.analytics.FirebaseAnalytics
import com.jakewharton.threetenabp.AndroidThreeTen
import com.mmlotte.app.base.di.AppInjector
import com.mmlotte.lottery.di.DaggerAppComponent
import com.mmlotte.lottery.helper.AdMobConstant
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import timber.log.Timber.DebugTree
import javax.inject.Inject

/**
 * Created by Vincent on 12/6/18
 */
class MMLotteApp : MultiDexApplication(), HasActivityInjector {

  @Inject
  lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

  override fun onCreate() {
    super.onCreate()

    DaggerAppComponent.builder()
      .application(this)
      .build()
      .inject(this)

    AppInjector.initAutoInjection(this)

//    if (LeakCanary.isInAnalyzerProcess(this)) {
//      // This process is dedicated to LeakCanary for heap analysis.
//      // You should not init your app in this process.
//      return
//    }
//    LeakCanary.install(this)

    if (BuildConfig.DEBUG) {
      Timber.plant(DebugTree())
//      Stetho.initializeWithDefaults(this)
    }


    MobileAds.initialize(this, AdMobConstant.AD_MOB_APP_ID)
    if (!BuildConfig.DEBUG) {
      FirebaseAnalytics.getInstance(this).setAnalyticsCollectionEnabled(true)
    }

    AndroidThreeTen.init(this)
  }

  override fun activityInjector(): AndroidInjector<Activity> = dispatchingActivityInjector

}