package com.mmlotte.app.base.di.module

import com.mmlotte.lottery.data.cache.di.CacheDataSourceModule
import com.mmlotte.lottery.data.impl.consent.RealConsentRepository
import com.mmlotte.lottery.data.impl.result.RealResultRepository
import com.mmlotte.lottery.data.network.di.NetworkDataSourceModule
import com.mmlotte.lottery.domain.repository.ConsentRepository
import com.mmlotte.lottery.domain.repository.ResultRepository
import dagger.Binds
import dagger.Module

/**
 * Created by Vincent on 12/6/18
 */
@Module(includes = [NetworkDataSourceModule::class, CacheDataSourceModule::class])
abstract class RepositoryModule {

  @Binds
  abstract fun resultRepository(resultRepository: RealResultRepository): ResultRepository

  @Binds
  abstract fun consentRepository(consentRepository: RealConsentRepository): ConsentRepository

}