package com.mmlotte.lottery.feature.listing.model

/**
 * Created by Vincent on 12/7/18
 */
data class PrizeViewItem(
  val prizeId: Int,
  val prizeTitle: String,
  val winningNumberNumbers: List<WinningNumberViewItem>
)