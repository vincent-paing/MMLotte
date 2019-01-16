package com.mmlotte.lottery.feature.listing.viewmodel

import com.mmlotte.app.base.helper.FontConversionHelper
import com.mmlotte.lottery.domain.MapFunction
import com.mmlotte.lottery.domain.helper.MyanmarNumberConverter
import com.mmlotte.lottery.feature.listing.model.WinningNumberViewItem
import javax.inject.Inject

/**
 * Created by Vincent on 12/14/18
 */
class WinningNumberViewItemMapper @Inject constructor(
  private val fontConversionHelper: FontConversionHelper
) : MapFunction<String, WinningNumberViewItem> {

  private val regex = Regex("(\\D*)(\\d*)")

  override fun map(item: String): WinningNumberViewItem {
    regex.matchEntire(item)?.let {
      val groupValues = it.groupValues

      return WinningNumberViewItem(
        character = fontConversionHelper.convertIfNeded(groupValues[1]),
        number = fontConversionHelper.convertIfNeded(MyanmarNumberConverter.getMMNumber(groupValues[2]))
      )
    } ?: throw IllegalArgumentException()
  }

}