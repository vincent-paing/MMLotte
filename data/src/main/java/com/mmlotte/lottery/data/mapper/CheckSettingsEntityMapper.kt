package com.mmlotte.lottery.data.mapper

import com.mmlotte.lottery.data.entity.CheckLotterySettingsEntity
import com.mmlotte.lottery.domain.DeviceFontStatusProvider
import com.mmlotte.lottery.domain.MapFunction
import com.mmlotte.lottery.domain.model.CheckSettings
import org.threeten.bp.ZoneId
import javax.inject.Inject

/**
 * Created by Vincent on 12/12/18
 */
class CheckSettingsEntityMapper @Inject constructor(
  private val deviceFontStatusProvider: DeviceFontStatusProvider
) :
  MapFunction<CheckLotterySettingsEntity, CheckSettings> {

  override fun map(item: CheckLotterySettingsEntity): CheckSettings {
    return CheckSettings(
      latestDate = item.date.atZone(ZoneId.of("Asia/Rangoon")),
      possibleCharactersForInput = item.possibleCharacters
    )
  }
}

