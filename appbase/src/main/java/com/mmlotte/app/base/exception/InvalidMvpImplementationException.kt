package com.mmlotte.app.base.exception

/**
 * Created by Vincent on 12/7/18
 */
class InvalidMvpImplementationException : Throwable() {

  override val message: String?
    get() = "MVP implementation is not correctly implemented"

}