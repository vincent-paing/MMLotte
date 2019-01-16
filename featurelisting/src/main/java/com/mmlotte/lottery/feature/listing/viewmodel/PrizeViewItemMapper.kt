package com.mmlotte.lottery.feature.listing.viewmodel

import com.mmlotte.app.base.helper.FontConversionHelper
import com.mmlotte.lottery.domain.MapFunction
import com.mmlotte.lottery.domain.model.Prize
import com.mmlotte.lottery.feature.listing.model.PrizeViewItem
import javax.inject.Inject

/**
 * Created by Vincent on 12/14/18
 */
class PrizeViewItemMapper @Inject constructor(
  private val fontConversionHelper: FontConversionHelper,
  private val winningNumberViewItemMapper: WinningNumberViewItemMapper
) : MapFunction<Prize, PrizeViewItem> {
  override fun map(item: Prize): PrizeViewItem {
    return PrizeViewItem(
      prizeId = item.prizeId,
      prizeTitle = fontConversionHelper.convertIfNeded(item.prizeTitle),
      winningNumberNumbers = item.winningNumbers.map(winningNumberViewItemMapper::map)
    )
  }

}