package com.mmlotte.lottery.data.di

import com.mmlotte.lottery.data.datasource.FakeResultNetworkDataSource
import com.mmlotte.lottery.data.datasource.network.ResultNetworkDataSource
import com.squareup.moshi.Moshi
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Vincent on 12/6/18
 */
@Module(includes = [FakeNetworkModule.Provider::class])
abstract class FakeNetworkModule {

  @Binds
  abstract fun resultNetworkDataSource(resultNetworkDataSource: FakeResultNetworkDataSource): ResultNetworkDataSource

  @Module
  object Provider {

    @Provides
    @JvmStatic
    @Singleton
    fun moshi(): Moshi {
      return Moshi.Builder().build()
    }

  }
}
