package com.mmlotte.app.base.helper

import com.aungkyawpaing.rabbkt.RabbktConverter
import com.mmlotte.lottery.domain.DeviceFontStatusProvider
import com.mmlotte.lottery.domain.DeviceFontStatusProvider.FontStatus.UNCIODE
import com.mmlotte.lottery.domain.DeviceFontStatusProvider.FontStatus.ZAWGYI
import javax.inject.Inject

/**
 * Created by Vincent on 12/14/18
 */
class FontConversionHelper @Inject constructor(
  private val deviceFontStatusProvider: DeviceFontStatusProvider
) {

  /**
   * Automatically convert depending on what {DeviceFontStatusProvider} returns
   */
  fun convertIfNeded(charSequence: CharSequence): String {

    return when (deviceFontStatusProvider.getFontStatus()) {
      ZAWGYI -> RabbktConverter.unicodeToZawgyi(charSequence.toString()).replace("null", "")
      UNCIODE -> charSequence.toString()
    }

  }

}