package com.mmlotte.lottery.view

import com.mmlotte.app.base.core.mvp.Viewable

/**
 * Created by Vincent on 12/13/18
 */
interface MainView : Viewable {

  fun showConsentDialogForAd()

  fun showConsentDialogForCrashLog()

  fun setUpCrashLogging()

  fun setUpAd(allowPersonalizedAd: Boolean)

}