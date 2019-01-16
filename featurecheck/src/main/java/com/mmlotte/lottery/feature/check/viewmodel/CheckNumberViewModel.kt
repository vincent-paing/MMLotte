package com.mmlotte.lottery.feature.check.viewmodel

import androidx.lifecycle.MutableLiveData
import com.mmlotte.app.base.core.mvp.BaseViewModel
import com.mmlotte.app.base.helper.AsyncViewResource
import com.mmlotte.app.base.helper.FontConversionHelper
import com.mmlotte.lottery.domain.exception.IllegalLotteryNumberInputException
import com.mmlotte.lottery.domain.interactor.consent.GetConsentStatusForAd
import com.mmlotte.lottery.domain.interactor.result.CheckLottery
import com.mmlotte.lottery.domain.interactor.result.GetSettingsForLotteryCheck
import com.mmlotte.lottery.feature.check.ads.AdShowStatusChecker
import com.mmlotte.lottery.feature.check.model.CheckSettingsViewItem
import com.mmlotte.lottery.feature.check.model.ViewItemMapper
import com.mmlotte.lottery.feature.check.view.CheckNumberView
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

/**
 * Created by Vincent on 12/11/18
 */
class CheckNumberViewModel @Inject constructor(
  private val checkLottery: CheckLottery,
  private val getSettingsForLotteryCheck: GetSettingsForLotteryCheck,
  private val viewItemMapper: ViewItemMapper,
  private val adShowStatusChecker: AdShowStatusChecker,
  private val getConsentStatusForAd: GetConsentStatusForAd,
  private val fontConversionHelper: FontConversionHelper
) : BaseViewModel<CheckNumberView>() {

  internal val settingsLiveData = MutableLiveData<AsyncViewResource<CheckSettingsViewItem>>()

  override fun attachView(viewable: CheckNumberView) {
    super.attachView(viewable)
    view?.subscribeToSettingsLiveData(settingsLiveData)
  }

  fun getSettings() {
    settingsLiveData.postValue(AsyncViewResource.loading())

    getSettingsForLotteryCheck.execute(Unit).map(viewItemMapper::map).subscribeBy(
      onSuccess = {
        settingsLiveData.postValue(AsyncViewResource.success(it))
      },
      onError = {
        settingsLiveData.postValue(AsyncViewResource.error(it))
      }
    )
  }

  fun getConsentStatusForAd() {
    getConsentStatusForAd.execute(Unit).subscribeBy {
      view?.preLoadAd(it)
    }
  }

  fun checkNumberForResult(character: String, lotteryNumber: String) {
    view?.showCheckLoading()

    if (adShowStatusChecker.shouldShowAd()) {
      view?.showAd()
    }
    checkLottery.execute(
      CheckLottery.Params(
        fontConversionHelper.convertIfNeded(character),
        fontConversionHelper.convertIfNeded(lotteryNumber)
      )
    ).subscribeBy(
      onSuccess = {
        view?.showResult(it.copy(prizeTitle = fontConversionHelper.convertIfNeded(it.prizeTitle)))

      },
      onError = {

        if (it is IllegalLotteryNumberInputException) {
          view?.showInputError(it)
        } else {
          view?.showCheckError(it)
        }
      }, onComplete = {
        view?.showNoResult()
      }
    ).addTo(compositeDisposable)
  }

}

