package com.mmlotte.lottery.viewmodel

import com.mmlotte.app.base.core.mvp.BaseViewModel
import com.mmlotte.lottery.domain.interactor.consent.CheckConsentToAsk
import com.mmlotte.lottery.domain.interactor.consent.GetConsentStatusForAd
import com.mmlotte.lottery.domain.interactor.consent.GetConsentStatusForCrashLog
import com.mmlotte.lottery.domain.interactor.consent.UpdateConsentStatusForAd
import com.mmlotte.lottery.domain.interactor.consent.UpdateConsentStatusForCrashLog
import com.mmlotte.lottery.domain.model.ConsentType
import com.mmlotte.lottery.view.MainView
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

/**
 * Created by Vincent on 12/13/18
 */
class MainViewModel @Inject constructor(
  private val checkConsentToAsk: CheckConsentToAsk,
  private val updateConsentStatusForCrashLog: UpdateConsentStatusForCrashLog,
  private val updateConsentStatusForAd: UpdateConsentStatusForAd,
  private val getConsentStatusForCrashLog: GetConsentStatusForCrashLog,
  private val getConsentStatusForAd: GetConsentStatusForAd
) : BaseViewModel<MainView>() {

  fun checkForConsents() {
    checkConsentToAsk.execute(Unit).subscribeBy(
      onSuccess = {
        when (it) {
          ConsentType.CRASH_LOGGING -> view?.showConsentDialogForCrashLog()
          ConsentType.ADS -> {
            view?.showConsentDialogForAd()
          }
        }
      }, onComplete = {
        getConsentForCrashLog()
        getConsentForAd()
      }
    ).addTo(compositeDisposable)
  }

  fun updateConsentStatusForCrashLog(hasConsented: Boolean) {
    updateConsentStatusForCrashLog.execute(hasConsented).subscribeBy {
      getConsentForCrashLog()
    }.addTo(compositeDisposable)
  }

  fun updateConsentStatusForAd(allowPersonalized: Boolean) {

    updateConsentStatusForAd.execute(allowPersonalized).subscribeBy {
      getConsentForAd()
    }.addTo(compositeDisposable)
  }

  fun getConsentForCrashLog() {
    getConsentStatusForCrashLog.execute(Unit).subscribeBy {
      if (it) {
        view?.setUpCrashLogging()
      }
    }.addTo(compositeDisposable)
  }

  fun getConsentForAd() {
    getConsentStatusForAd.execute(Unit).subscribeBy {
      view?.setUpAd(it)
    }.addTo(compositeDisposable)
  }

}