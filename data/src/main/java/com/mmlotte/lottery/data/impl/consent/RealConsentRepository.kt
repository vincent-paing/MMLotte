package com.mmlotte.lottery.data.impl.consent

import com.mmlotte.lottery.data.datasource.consent.ConsentCacheDataSource
import com.mmlotte.lottery.domain.repository.ConsentRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Vincent on 12/13/18
 */
class RealConsentRepository @Inject constructor(
  private val consentCache: ConsentCacheDataSource
) : ConsentRepository {

  override fun getHasAskedForLogCrashConsent(): Single<Boolean> {
    return Single.fromCallable {
      consentCache.hasAskedConsentForCrashLogging()
    }
  }

  override fun getConsentStatusForLoggingCrash(): Single<Boolean> {
    return Single.fromCallable {
      consentCache.getUserConsentForCrashLogging()
    }
  }

  override fun updateConsentStatusForLoggingCrash(newVal: Boolean): Completable {
    return Completable.fromCallable {
      consentCache.putUserConsentForCrashLogging(newVal)
      consentCache.putHasAskedConsentForCrashLogging(true)
    }
  }

  override fun getConsentStatusForAd(): Single<Boolean> {
    return Single.fromCallable {
      consentCache.getUserConsentForAd()
    }
  }

  override fun getHasAskedForAdConsent(): Single<Boolean> {
    return Single.fromCallable {
      consentCache.hasAskedForAdMob()
    }
  }

  override fun updateConsentStatusForAd(newVal: Boolean): Completable {
    return Completable.fromCallable {
      consentCache.putUserConsentForAd(newVal)
      consentCache.putHasAskedForAd(true)
    }
  }

  override fun updateAskedStatusForAdMob(newVal: Boolean): Completable {
    return Completable.fromCallable {
      consentCache.putHasAskedForAd(newVal)
    }
  }

}