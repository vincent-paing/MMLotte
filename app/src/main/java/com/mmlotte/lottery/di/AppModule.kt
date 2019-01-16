package com.mmlotte.lottery.di

import com.mmlotte.app.base.di.module.BaseAppModule
import com.mmlotte.app.base.di.module.RepositoryModule
import com.mmlotte.app.base.di.viewmodel.ViewModelFactoryModule
import com.mmlotte.lottery.domain.DeviceFontStatusProvider
import com.mmlotte.lottery.feature.check.di.FeatureCheckNumberModule
import com.mmlotte.lottery.feature.listing.di.FeatureListingModule
import com.mmlotte.lottery.feature.settings.di.FeatureSettingsModule
import com.mmlotte.lottery.font.MDetectDeviceFontStatusProvider
import dagger.Binds
import dagger.Module

/**
 * Created by Vincent on 12/11/18
 */
@Module(
  includes = [BaseAppModule::class,
    FeatureListingModule::class,
    FeatureCheckNumberModule::class,
    FeatureSettingsModule::class,
    RepositoryModule::class,
    ViewModelFactoryModule::class,
    ActivityModule::class]
)
abstract class AppModule {

  @Binds
  abstract fun deviceFontStatusProvider(deviceFontStatusProvider: MDetectDeviceFontStatusProvider): DeviceFontStatusProvider

}

