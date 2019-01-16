package com.mmlotte.app.base.exception

import android.content.Context
import com.mmlotte.app.base.R
import com.mmlotte.lottery.data.network.exception.NetworkException
import com.mmlotte.lottery.data.network.exception.NetworkExceptionMessageFactory
import com.mmlotte.lottery.domain.exception.GenericErrorMessageFactory
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

/**
 * Created by Vincent on 12/12/18
 */
class RealGenericErrorMessageFactory @Inject constructor(
  private val context: Context,
  private val networkExceptionMessageFactory: NetworkExceptionMessageFactory
) : GenericErrorMessageFactory {

  override fun getErrorMessage(throwable: Throwable): CharSequence {

    return when (throwable) {
      is UnknownHostException -> networkExceptionMessageFactory.getErrorMessage(throwable)
      is SocketTimeoutException -> networkExceptionMessageFactory.getErrorMessage(throwable)
      is ConnectException -> networkExceptionMessageFactory.getErrorMessage(throwable)
      is NetworkException -> networkExceptionMessageFactory.getErrorMessage(throwable)
      else -> {
        throwable.message ?: context.getString(R.string.error_generic)
      }

    }

  }

}