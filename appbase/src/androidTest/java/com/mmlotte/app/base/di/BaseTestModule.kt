package com.mmlotte.app.base.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.mmlotte.app.base.di.module.DataSourceModule
import com.mmlotte.app.base.di.module.RepositoryModule
import com.mmlotte.app.base.idling.IdlingPostExecutionThread
import com.mmlotte.app.base.idling.IdlingThreadExecutor
import com.mmlotte.lottery.domain.executor.PostExecutionThread
import com.mmlotte.lottery.domain.executor.ThreadExecutor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Vincent on 12/6/18
 */
@Module(includes = [RepositoryModule::class, DataSourceModule::class])
class BaseTestModule {

  @Provides fun context(application: Application): Context {
    return application.applicationContext
  }

  @Provides @Singleton
  fun sharedPref(context: Context): SharedPreferences {
    return PreferenceManager.getDefaultSharedPreferences(context)
  }

  @Provides @Singleton fun threadExecutor(): ThreadExecutor {
    return IdlingThreadExecutor()
  }

  @Provides @Singleton fun postExecutionThread(): PostExecutionThread {
    return IdlingPostExecutionThread()
  }

}