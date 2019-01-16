package com.mmlotte.lottery.domain.model

import org.threeten.bp.ZonedDateTime

/**
 * Created by Vincent on 12/12/18
 */
data class CheckSettings(
  val latestDate: ZonedDateTime,
  val possibleCharactersForInput: List<String>
)