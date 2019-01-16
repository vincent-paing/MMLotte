package com.mmlotte.lottery.feature.listing.viewmodel

import androidx.lifecycle.MutableLiveData
import com.mmlotte.app.base.core.mvp.BaseViewModel
import com.mmlotte.app.base.helper.AsyncViewResource
import com.mmlotte.lottery.domain.interactor.consent.GetConsentStatusForAd
import com.mmlotte.lottery.domain.interactor.result.GetLatestResult
import com.mmlotte.lottery.feature.listing.model.ResultViewItem
import com.mmlotte.lottery.feature.listing.view.ResultListingView
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

/**
 * Created by Vincent on 12/7/18
 */
class ResultListingViewModel
@Inject constructor(
  private val getLatestResult: GetLatestResult,
  private val getConsentStatusForAd: GetConsentStatusForAd,
  private val resultViewItemMapper: ResultViewItemMapper
) : BaseViewModel<ResultListingView>() {

  internal val liveData = MutableLiveData<AsyncViewResource<ResultViewItem>>()

  override fun attachView(viewable: ResultListingView) {
    super.attachView(viewable)
    view?.subscribeToLiveData(liveData)
  }

  fun getLatestResult() {
    liveData.postValue(AsyncViewResource.loading())

    getLatestResult.execute(Unit).map(resultViewItemMapper::map)
      .subscribeBy(
        onSuccess = { lotteryResult ->
          liveData.postValue(AsyncViewResource.success(lotteryResult))
        },
        onError = { error ->
          liveData.postValue(AsyncViewResource.error(error))
        }
      ).addTo(compositeDisposable)
  }

  fun getConsentStatusForAd() {
    getConsentStatusForAd.execute(Unit).subscribeBy {
      view?.showAd(it)
    }
  }

}
