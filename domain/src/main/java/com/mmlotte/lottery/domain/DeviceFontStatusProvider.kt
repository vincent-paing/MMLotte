package com.mmlotte.lottery.domain

/**
 * Created by Vincent on 12/14/18
 */
interface DeviceFontStatusProvider {

  enum class FontStatus {
    ZAWGYI,
    UNCIODE
  }

  fun getFontStatus(): FontStatus

}
