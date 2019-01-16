package com.mmlotte.lottery.data.network.exception

import okhttp3.ResponseBody
import java.io.IOException

/**
 * Created by Vincent on 9/20/18
 */
data class NetworkException constructor(
  val errorBody: ResponseBody? = null,
  var errorCode: Int = 0
) : IOException()