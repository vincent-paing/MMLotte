package com.mmlotte.lottery.data.cache.di

import com.mmlotte.lottery.data.cache.datasource.RealConsentCacheDataSource
import com.mmlotte.lottery.data.datasource.consent.ConsentCacheDataSource
import dagger.Binds
import dagger.Module

/**
 * Created by Vincent on 12/13/18
 */
@Module
abstract class CacheDataSourceModule {

  @Binds
  abstract fun consentCacheDataSource(consentCacheDataSourceModule: RealConsentCacheDataSource): ConsentCacheDataSource
}

