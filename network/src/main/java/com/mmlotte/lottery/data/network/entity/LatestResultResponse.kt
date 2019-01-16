package com.mmlotte.lottery.data.network.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by Vincent on 9/20/18
 */
@JsonClass(generateAdapter = true)
data class LatestResultResponse(
  @Json(name = "date") val date: Long,
  @Json(name = "results") val prizeList: List<PrizeResponse>
)