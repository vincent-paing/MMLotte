package com.mmlotte.lottery.feature.check.model

import com.mmlotte.app.base.helper.FontConversionHelper
import com.mmlotte.lottery.domain.MapFunction
import com.mmlotte.lottery.domain.model.CheckSettings
import org.threeten.bp.format.DateTimeFormatter
import javax.inject.Inject

/**
 * Created by Vincent on 12/12/18
 */
class ViewItemMapper @Inject constructor(
  private val fontConversionHelper: FontConversionHelper
) : MapFunction<CheckSettings, CheckSettingsViewItem> {

  private val dateFormatter = DateTimeFormatter.ofPattern("MMMM YYYY")

  override fun map(item: CheckSettings): CheckSettingsViewItem {
    return CheckSettingsViewItem(
      dateString = item.latestDate.format(dateFormatter).toUpperCase(),
      characters = item.possibleCharactersForInput.map {
        fontConversionHelper.convertIfNeded(it)
      }
    )
  }

}