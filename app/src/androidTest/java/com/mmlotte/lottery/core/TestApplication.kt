package com.mmlotte.lottery.core

import android.app.Activity
import android.app.Application
import android.app.Service
import com.mmlotte.app.base.di.AppInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.HasServiceInjector
import javax.inject.Inject

/**
 * Created by Vincent on 12/6/18
 */
class TestApplication : Application(), HasActivityInjector, HasServiceInjector {
  @Inject
  lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

  @Inject
  lateinit var dispatchingServiceInjector: DispatchingAndroidInjector<Service>

//  companion object {
//    lateinit var appComponent: AppComponent
//  }

  override fun onCreate() {
    super.onCreate()
    AppInjector.initAutoInjection(this)
  }

  override fun activityInjector(): AndroidInjector<Activity> = dispatchingActivityInjector

  override fun serviceInjector(): AndroidInjector<Service> = dispatchingServiceInjector
}

