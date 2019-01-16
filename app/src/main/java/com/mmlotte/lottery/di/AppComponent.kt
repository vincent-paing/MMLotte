package com.mmlotte.lottery.di

import android.app.Application
import com.mmlotte.lottery.MMLotteApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by Vincent on 12/6/18
 */
@Singleton
@Component(
  modules = [AppModule::class,
    AndroidInjectionModule::class,
    AndroidSupportInjectionModule::class]
)
interface AppComponent {

  @Component.Builder
  interface Builder {
    @BindsInstance
    fun application(application: Application): Builder

    fun build(): AppComponent
  }

  fun inject(application: MMLotteApp)

}