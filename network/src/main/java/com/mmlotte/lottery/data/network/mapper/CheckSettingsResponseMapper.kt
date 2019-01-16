package com.mmlotte.lottery.data.network.mapper

import com.mmlotte.lottery.data.entity.CheckLotterySettingsEntity
import com.mmlotte.lottery.data.network.entity.CheckSettingsResponse
import com.mmlotte.lottery.domain.MapFunction
import org.threeten.bp.Instant
import javax.inject.Inject

/**
 * Created by Vincent on 12/12/18
 */
class CheckSettingsResponseMapper
@Inject
constructor() :
  MapFunction<CheckSettingsResponse, CheckLotterySettingsEntity> {

  override fun map(item: CheckSettingsResponse): CheckLotterySettingsEntity {
    return CheckLotterySettingsEntity(
      date = Instant.ofEpochSecond(item.latestDataTimeStamp),
      possibleCharacters = item.characters
    )
  }
}
