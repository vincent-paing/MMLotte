package com.mmlotte.app.base.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.mmlotte.app.base.UIThread
import com.mmlotte.app.base.exception.RealGenericErrorMessageFactory
import com.mmlotte.app.base.exception.RealNetworkExceptionMessageFactory
import com.mmlotte.lottery.data.JobExecutor
import com.mmlotte.lottery.data.network.exception.NetworkExceptionMessageFactory
import com.mmlotte.lottery.domain.exception.GenericErrorMessageFactory
import com.mmlotte.lottery.domain.executor.PostExecutionThread
import com.mmlotte.lottery.domain.executor.ThreadExecutor
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Vincent on 12/6/18
 */
@Module(includes = [BaseAppModule.Provider::class])
abstract class BaseAppModule {

  @Binds
  abstract fun threadExecutor(jobExecutor: JobExecutor): ThreadExecutor

  @Binds
  abstract fun postExecutionThread(uiThread: UIThread): PostExecutionThread

  @Binds
  abstract fun genericErrorMessageFactory(realGenericErrorMessageFactory: RealGenericErrorMessageFactory): GenericErrorMessageFactory

  @Binds
  abstract fun networkErrorMessageFactory(realNetworkExceptionMessageFactory: RealNetworkExceptionMessageFactory): NetworkExceptionMessageFactory

  @Module
  object Provider {

    @Provides @JvmStatic @Singleton fun context(application: Application): Context {
      return application.applicationContext
    }

    @Provides @JvmStatic @Singleton
    fun sharedPref(context: Context): SharedPreferences {
      return PreferenceManager.getDefaultSharedPreferences(context)
    }

  }

}

