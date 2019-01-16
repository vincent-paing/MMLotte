package com.mmlotte.lottery.data.network.di

import com.mmlotte.lottery.data.network.BuildConfig
import com.mmlotte.lottery.data.network.retrofit.MMLotteApi
import com.mmlotte.lottery.data.network.retrofit.NetworkErrorInterceptor
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import okhttp3.logging.HttpLoggingInterceptor.Level.NONE
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * Created by Vincent on 9/20/18
 */
@Module
class RetrofitModule {

  @Provides @Singleton fun moshi(): Moshi {
    return Moshi.Builder().build()
  }

  @Provides @Singleton fun okHttpClient(): OkHttpClient {

    val builder = OkHttpClient.Builder()

    val loggerInterceptor = HttpLoggingInterceptor()

    if (BuildConfig.DEBUG) {
      loggerInterceptor.level = BODY
    } else {
      loggerInterceptor.level = NONE
    }

    builder
      .addInterceptor(NetworkErrorInterceptor())
      .addInterceptor(loggerInterceptor)

    return builder.build()
  }

  @Provides @Singleton fun retrofit(moshi: Moshi, okHttpClient: OkHttpClient): Retrofit {
    val builder = Retrofit.Builder()

    builder.baseUrl("https://mmlotte.herokuapp.com/")
      .client(okHttpClient)
      .addConverterFactory(MoshiConverterFactory.create(moshi))

    return builder.build()
  }

  @Provides @Singleton fun mmLotteApi(retrofit: Retrofit): MMLotteApi {
    return retrofit.create(MMLotteApi::class.java)
  }

}