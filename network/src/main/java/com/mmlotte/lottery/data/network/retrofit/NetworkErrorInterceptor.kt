package com.mmlotte.lottery.data.network.retrofit

import com.mmlotte.lottery.data.network.exception.NetworkException
import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Response

/**
 * Created by Vincent on 9/20/18
 */

class NetworkErrorInterceptor : Interceptor {

  override fun intercept(chain: Chain?): Response {
    val response = chain!!.proceed(chain.request())
    if (response.isSuccessful) {
      return response
    } else {
      throw NetworkException(response.body(), response.code())
    }
  }

}