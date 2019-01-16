package com.mmlotte.lottery.feature.listing.viewmodel

import com.mmlotte.lottery.domain.MapFunction
import com.mmlotte.lottery.domain.model.LotteryResult
import com.mmlotte.lottery.feature.listing.model.ResultViewItem
import org.threeten.bp.format.DateTimeFormatter
import javax.inject.Inject

/**
 * Created by Vincent on 12/14/18
 */
class ResultViewItemMapper @Inject constructor(
  private val prizeViewItemMapper: PrizeViewItemMapper
) : MapFunction<LotteryResult, ResultViewItem> {
  private val dateFormatter = DateTimeFormatter.ofPattern("MMMM YYYY")

  override fun map(item: LotteryResult): ResultViewItem {
    return ResultViewItem(
      resultDate = item.resultDate.format(dateFormatter).toUpperCase(),
      prizeList = item.prizeList.map(prizeViewItemMapper::map)
    )
  }
}