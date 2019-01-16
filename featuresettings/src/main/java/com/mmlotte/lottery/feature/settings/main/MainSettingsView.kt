package com.mmlotte.lottery.feature.settings.main

import com.mmlotte.app.base.core.mvp.Viewable

/**
 * Created by Vincent on 12/14/18
 */
interface MainSettingsView : Viewable {

  fun showPrivacyPolicy()

  fun showTermsAndConditions()

  fun showLicense()

  fun showConsentSettings()

  fun showBuildVersion()

}