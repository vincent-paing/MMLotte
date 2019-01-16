package com.mmlotte.lottery.data.network.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by Vincent on 12/12/18
 */
@JsonClass(generateAdapter = true)
data class CheckSettingsResponse(
  @Json(name = "date") val latestDataTimeStamp: Long,
  @Json(name = "characters") val characters: List<String>
)