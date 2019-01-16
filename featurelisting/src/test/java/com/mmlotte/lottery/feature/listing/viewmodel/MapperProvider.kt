package com.mmlotte.lottery.feature.listing.viewmodel

import com.mmlotte.app.base.helper.FontConversionHelper
import com.mmlotte.lottery.domain.DeviceFontStatusProvider
import com.mmlotte.lottery.domain.DeviceFontStatusProvider.FontStatus
import com.mmlotte.lottery.domain.DeviceFontStatusProvider.FontStatus.UNCIODE

/**
 * Created by Vincent on 12/14/18
 */
object MapperProvider {

  fun resultViewItemMapper(): ResultViewItemMapper {
    return ResultViewItemMapper(prizeViewItemMapper())
  }

  fun prizeViewItemMapper(): PrizeViewItemMapper {
    return PrizeViewItemMapper(
      getFakeFontConversionHelper(),
      winningNumberViewItemMapper()
    )
  }

  fun winningNumberViewItemMapper(): WinningNumberViewItemMapper {
    return WinningNumberViewItemMapper(getFakeFontConversionHelper())
  }

  fun getFakeFontConversionHelper(): FontConversionHelper {
    return FontConversionHelper(object : DeviceFontStatusProvider {
      override fun getFontStatus(): FontStatus {
        return UNCIODE
      }
    })
  }
}