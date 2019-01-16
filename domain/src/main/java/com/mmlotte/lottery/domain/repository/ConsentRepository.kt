package com.mmlotte.lottery.domain.repository

import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by Vincent on 12/13/18
 */
interface ConsentRepository {

  fun getHasAskedForLogCrashConsent(): Single<Boolean>

  fun getConsentStatusForLoggingCrash(): Single<Boolean>

  fun updateConsentStatusForLoggingCrash(newVal: Boolean): Completable

  fun getConsentStatusForAd(): Single<Boolean>

  fun updateConsentStatusForAd(newVal: Boolean): Completable

  fun getHasAskedForAdConsent(): Single<Boolean>

  fun updateAskedStatusForAdMob(newVal: Boolean): Completable

}