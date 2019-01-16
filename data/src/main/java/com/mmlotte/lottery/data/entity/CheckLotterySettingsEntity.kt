package com.mmlotte.lottery.data.entity

import org.threeten.bp.Instant

/**
 * Created by Vincent on 12/12/18
 */
data class CheckLotterySettingsEntity(
  val possibleCharacters: List<String>,
  val date: Instant
)