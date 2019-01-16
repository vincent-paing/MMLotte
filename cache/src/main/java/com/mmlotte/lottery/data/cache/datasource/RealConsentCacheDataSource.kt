package com.mmlotte.lottery.data.cache.datasource

import android.content.SharedPreferences
import androidx.core.content.edit
import com.mmlotte.lottery.data.datasource.consent.ConsentCacheDataSource
import javax.inject.Inject

/**
 * Created by Vincent on 12/13/18
 */
class RealConsentCacheDataSource @Inject constructor(
  private val sharedPreferences: SharedPreferences
) : ConsentCacheDataSource {
  companion object {
    private const val KEY_CRASH_LOG_CONSENT = "CRASH_LOG_CONSENT"
    private const val KEY_CRASH_LOG_CONSENT_ASK = "CRASH_LOG_CONSENT_ASK"
    private const val KEY_AD_MOB_ASK = "AD_MOB_ASK"
    private const val KEY_AD_CONSENT = "AD_CONSENT"
  }

  override fun putUserConsentForCrashLogging(hasConsented: Boolean) {
    sharedPreferences.edit {
      putBoolean(KEY_CRASH_LOG_CONSENT, hasConsented)
    }
  }

  override fun getUserConsentForCrashLogging(): Boolean {
    return sharedPreferences.getBoolean(KEY_CRASH_LOG_CONSENT, false)
  }

  override fun hasAskedConsentForCrashLogging(): Boolean {
    return sharedPreferences.getBoolean(KEY_CRASH_LOG_CONSENT_ASK, false)
  }

  override fun putHasAskedConsentForCrashLogging(askedValue: Boolean) {
    sharedPreferences.edit {
      putBoolean(KEY_CRASH_LOG_CONSENT_ASK, askedValue)
    }
  }

  override fun hasAskedForAdMob(): Boolean {
    return sharedPreferences.getBoolean(KEY_AD_MOB_ASK, false)
  }

  override fun putHasAskedForAd(askedValue: Boolean) {
    sharedPreferences.edit {
      putBoolean(KEY_AD_MOB_ASK, askedValue)
    }
  }

  override fun putUserConsentForAd(newVal: Boolean) {
    sharedPreferences.edit {
      putBoolean(KEY_AD_CONSENT, newVal)
    }
  }

  override fun getUserConsentForAd(): Boolean {
    return sharedPreferences.getBoolean(KEY_AD_CONSENT, false)
  }
}

