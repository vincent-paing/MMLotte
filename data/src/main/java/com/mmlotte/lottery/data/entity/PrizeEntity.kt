package com.mmlotte.lottery.data.entity

/**
 * Created by Vincent on 9/20/18
 */
data class PrizeEntity(
  val prizeId: Int,
  val prizeTitle: String,
  val winningNumbers: List<String>
)