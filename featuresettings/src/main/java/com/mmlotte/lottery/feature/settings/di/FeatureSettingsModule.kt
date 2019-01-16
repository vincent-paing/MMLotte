package com.mmlotte.lottery.feature.settings.di

import androidx.lifecycle.ViewModel
import com.mmlotte.app.base.di.viewmodel.ViewModelKey
import com.mmlotte.lottery.feature.settings.consent.ConsentSettingsActivity
import com.mmlotte.lottery.feature.settings.consent.ConsentSettingsViewModel
import com.mmlotte.lottery.feature.settings.main.MainSettingsFragment
import com.mmlotte.lottery.feature.settings.main.MainSettingsViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

/**
 * Created by Vincent on 12/14/18
 */
@Module
abstract class FeatureSettingsModule {

  @ContributesAndroidInjector
  abstract fun mainSettingsFragment(): MainSettingsFragment

  @Binds
  @IntoMap
  @ViewModelKey(MainSettingsViewModel::class)
  abstract fun mainSettingsViewModel(mainSettingsViewModel: MainSettingsViewModel): ViewModel

  @ContributesAndroidInjector
  abstract fun consentSettingsActivity(): ConsentSettingsActivity

  @Binds
  @IntoMap
  @ViewModelKey(ConsentSettingsViewModel::class)
  abstract fun consentSettingsViewModel(consentSettingsViewModel: ConsentSettingsViewModel): ViewModel

}
