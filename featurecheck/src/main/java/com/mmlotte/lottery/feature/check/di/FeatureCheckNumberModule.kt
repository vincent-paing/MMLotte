package com.mmlotte.lottery.feature.check.di

import androidx.lifecycle.ViewModel
import com.mmlotte.app.base.di.viewmodel.ViewModelKey
import com.mmlotte.lottery.feature.check.ads.AdShowCountingStatusChecker
import com.mmlotte.lottery.feature.check.ads.AdShowStatusChecker
import com.mmlotte.lottery.feature.check.view.CheckNumberFragment
import com.mmlotte.lottery.feature.check.viewmodel.CheckNumberViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

/**
 * Created by Vincent on 12/11/18
 */
@Module
abstract class FeatureCheckNumberModule {

  @ContributesAndroidInjector
  abstract fun checkNumberFragment(): CheckNumberFragment

  @Binds
  @IntoMap
  @ViewModelKey(CheckNumberViewModel::class)
  abstract fun checkNumberViewModel(checkNumberViewModel: CheckNumberViewModel): ViewModel

  @Binds
  abstract fun adShowStatusChecker(adShowStatusChecker: AdShowCountingStatusChecker): AdShowStatusChecker
}


