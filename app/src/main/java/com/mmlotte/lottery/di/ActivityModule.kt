package com.mmlotte.lottery.di

import androidx.lifecycle.ViewModel
import com.mmlotte.app.base.di.viewmodel.ViewModelKey
import com.mmlotte.lottery.SplashActivity
import com.mmlotte.lottery.view.MainActivity
import com.mmlotte.lottery.viewmodel.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

/**
 * Created by Vincent on 12/11/18
 */
@Module
abstract class ActivityModule {

  @ContributesAndroidInjector
  abstract fun mainActivity(): MainActivity

  @ContributesAndroidInjector
  abstract fun splashActivity(): SplashActivity

  @Binds
  @IntoMap
  @ViewModelKey(MainViewModel::class)
  abstract fun mainViewModel(mainViewModel: MainViewModel): ViewModel
}
