package com.mmlotte.lottery.data.network.di

import com.mmlotte.lottery.data.datasource.network.ResultNetworkDataSource
import com.mmlotte.lottery.data.network.datasource.RealResultNetworkDataSource
import dagger.Binds
import dagger.Module

/**
 * Created by Vincent on 9/20/18
 */
@Module(includes = [RetrofitModule::class])
abstract class NetworkDataSourceModule {

  @Binds
  abstract fun resultNetworkDataSource(resultNetworkDataSource: RealResultNetworkDataSource): ResultNetworkDataSource
}