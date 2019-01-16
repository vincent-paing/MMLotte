package com.mmlotte.lottery.data.network.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by Vincent on 9/20/18
 */
@JsonClass(generateAdapter = true)
data class PrizeResponse(
  @Json(name = "prize_id") val prizeId: Int,
  @Json(name = "name_mm") val nameMm: String,
  @Json(name = "winning_numbers") val winningNumbers: List<String>
)