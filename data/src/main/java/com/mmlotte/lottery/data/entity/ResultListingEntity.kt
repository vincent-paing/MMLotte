package com.mmlotte.lottery.data.entity

import org.threeten.bp.Instant

/**
 * Created by Vincent on 9/20/18
 */
data class ResultListingEntity(
  val resultDate: Instant,
  val prizeEntities: List<PrizeEntity>
)