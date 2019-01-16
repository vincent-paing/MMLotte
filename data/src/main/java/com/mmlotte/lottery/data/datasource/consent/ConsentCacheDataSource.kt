package com.mmlotte.lottery.data.datasource.consent

/**
 * Created by Vincent on 12/13/18
 */
interface ConsentCacheDataSource {

  fun putUserConsentForCrashLogging(hasConsented: Boolean)

  fun getUserConsentForCrashLogging(): Boolean

  fun hasAskedConsentForCrashLogging(): Boolean

  fun putHasAskedConsentForCrashLogging(askedValue: Boolean)

  fun hasAskedForAdMob(): Boolean

  fun putHasAskedForAd(askedValue: Boolean)

  fun putUserConsentForAd(newVal: Boolean)

  fun getUserConsentForAd(): Boolean

}