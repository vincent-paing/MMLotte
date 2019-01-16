package com.mmlotte.lottery.font

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import java.util.Locale
import javax.inject.Inject

/**
 * Created by Vincent on 12/14/18
 */
class FontUtils @Inject constructor(
  private val context: Context
) {

  fun updateToZawgyiLocale(
    activityContext: Activity
  ) {
    try {
      val config = Configuration(context.resources.configuration)
      config.locale = Locale("zg")
      context.getResources()
        .updateConfiguration(config, context.resources.displayMetrics)

      val activityConfig = Configuration(activityContext.resources.configuration)
      activityConfig.locale = Locale("zg")
      activityContext.resources
        .updateConfiguration(activityConfig, activityContext.resources.displayMetrics)
    } catch (e: Exception) {
      e.printStackTrace()
    }

  }

}