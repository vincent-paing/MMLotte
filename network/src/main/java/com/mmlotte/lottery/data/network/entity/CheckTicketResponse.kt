package com.mmlotte.lottery.data.network.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by Vincent on 12/11/18
 */
@JsonClass(generateAdapter = true)
data class CheckTicketResponse(
  @Json(name = "prize") val prize: PrizeResponse? = null,
  @Json(name = "win") val isAWinningTicket: Boolean
)
