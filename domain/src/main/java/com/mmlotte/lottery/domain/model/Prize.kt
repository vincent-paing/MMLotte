package com.mmlotte.lottery.domain.model

/**
 * Created by Vincent on 9/20/18
 */
data class Prize(
  val prizeId: Int,
  val prizeTitle: String,
  val winningNumbers: List<String>
)