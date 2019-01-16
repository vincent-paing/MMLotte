package com.mmlotte.lottery.data.network.exception

import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Created by Vincent on 12/12/18
 */
interface NetworkExceptionMessageFactory {

  fun getErrorMessage(networkException: NetworkException): CharSequence

  fun getErrorMessage(unknownHostException: UnknownHostException): CharSequence

  fun getErrorMessage(socketTimeoutException: SocketTimeoutException): CharSequence

  fun getErrorMessage(connectException: ConnectException): CharSequence

}