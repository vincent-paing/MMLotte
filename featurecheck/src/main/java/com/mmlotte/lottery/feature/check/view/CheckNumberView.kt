package com.mmlotte.lottery.feature.check.view

import androidx.lifecycle.LiveData
import com.mmlotte.app.base.core.mvp.Viewable
import com.mmlotte.app.base.helper.AsyncViewResource
import com.mmlotte.lottery.domain.exception.IllegalLotteryNumberInputException
import com.mmlotte.lottery.domain.model.Prize
import com.mmlotte.lottery.feature.check.model.CheckSettingsViewItem

/**
 * Created by Vincent on 12/11/18
 */
interface CheckNumberView : Viewable {

  fun showCheckError(error: Throwable)

  fun showNoResult()

  fun showResult(prize: Prize)

  fun showCheckLoading()

  fun showInputError(it: IllegalLotteryNumberInputException)

  fun subscribeToSettingsLiveData(liveData: LiveData<AsyncViewResource<CheckSettingsViewItem>>)

  fun showAd()
  
  fun preLoadAd(allowPersonalized: Boolean)

}
