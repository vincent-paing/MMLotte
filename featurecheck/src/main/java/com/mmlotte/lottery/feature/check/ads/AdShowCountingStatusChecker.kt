package com.mmlotte.lottery.feature.check.ads

import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Vincent on 12/13/18
 */
@Singleton
class AdShowCountingStatusChecker @Inject constructor() : AdShowStatusChecker {

  companion object {
    var counter = 1
    var multipler = 1
    const val EXPONENT = 5
  }

  override fun shouldShowAd(): Boolean {
    return if (counter < EXPONENT * multipler) {
      counter++
      Timber.i("ad click counter : $counter")
      false
    } else {
      counter = 1
      multipler++
      Timber.i("rest ad click counter")
      true
    }
  }

}