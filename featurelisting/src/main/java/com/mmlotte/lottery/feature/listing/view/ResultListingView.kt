package com.mmlotte.lottery.feature.listing.view

import androidx.lifecycle.LiveData
import com.mmlotte.app.base.core.mvp.Viewable
import com.mmlotte.app.base.helper.AsyncViewResource
import com.mmlotte.lottery.feature.listing.model.ResultViewItem

/**
 * Created by Vincent on 12/7/18
 */
interface ResultListingView : Viewable {

  fun subscribeToLiveData(liveData: LiveData<AsyncViewResource<ResultViewItem>>)

  fun showAd(allowPersonalized: Boolean)
}

