package com.mmlotte.lottery.domain.model

import org.threeten.bp.ZonedDateTime

/**
 * Created by Vincent on 9/20/18
 */
data class LotteryResult(
  val resultDate: ZonedDateTime,
  val prizeList: List<Prize>
)