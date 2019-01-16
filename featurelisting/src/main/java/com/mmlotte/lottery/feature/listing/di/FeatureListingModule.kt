package com.mmlotte.lottery.feature.listing.di

import androidx.lifecycle.ViewModel
import com.mmlotte.app.base.di.viewmodel.ViewModelKey
import com.mmlotte.lottery.feature.listing.view.ResultListingFragment
import com.mmlotte.lottery.feature.listing.viewmodel.ResultListingViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

/**
 * Created by Vincent on 12/11/18
 */
@Module
abstract class FeatureListingModule {

  @ContributesAndroidInjector
  abstract fun resultListingFragment(): ResultListingFragment

  @Binds
  @IntoMap
  @ViewModelKey(ResultListingViewModel::class)
  abstract fun resultListingViewModel(resultListingViewModel: ResultListingViewModel): ViewModel

}
