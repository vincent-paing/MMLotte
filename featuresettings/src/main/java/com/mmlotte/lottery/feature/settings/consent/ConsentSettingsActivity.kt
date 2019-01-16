/*
 * Copyright 2018 Aung Kyaw Paing  Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions
 * and limitations under the License.
 */

package com.mmlotte.lottery.feature.settings.consent

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.mmlotte.app.base.core.mvp.MvpActivity
import com.mmlotte.lottery.feature.settings.R
import kotlinx.android.synthetic.main.actvity_consent_settings.settingsItemViewAd
import kotlinx.android.synthetic.main.actvity_consent_settings.settingsItemViewCrashLog
import kotlinx.android.synthetic.main.actvity_consent_settings.switchAd
import kotlinx.android.synthetic.main.actvity_consent_settings.switchCrashLog
import kotlinx.android.synthetic.main.actvity_consent_settings.toolBar

/**
 * Created by Vincent on 8/20/18
 */
class ConsentSettingsActivity :
  MvpActivity<ConsentSettingsView, ConsentSettingsViewModel>(),
  ConsentSettingsView {

  override val viewModel: ConsentSettingsViewModel by contractedViewModels()

  companion object {
    fun newIntent(context: Context): Intent {
      return Intent(context, ConsentSettingsActivity::class.java)
    }

  }

  override val layoutResId: Int
    get() = R.layout.actvity_consent_settings

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setSupportActionBar(toolBar)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)

    switchCrashLog.setOnClickListener {
      viewModel.toggleConsentStatusForCrashLog()
    }

    settingsItemViewCrashLog.setOnClickListener {
      viewModel.toggleConsentStatusForCrashLog()
    }

    switchAd.setOnClickListener {
      viewModel.toggleConsentStatusForAd()
    }

    settingsItemViewAd.setOnClickListener {
      viewModel.toggleConsentStatusForAd()
    }

    if (savedInstanceState == null) {
      viewModel.getConsentStatus()
    }
  }

  override fun subscribeToCrashLogConsentLiveData(liveData: LiveData<Boolean>) {
    liveData.observe(this, Observer {
      switchCrashLog.isChecked = it
    })
  }

  override fun subscribeToAdConsentLiveData(liveData: LiveData<Boolean>) {
    liveData.observe(this, Observer {
      switchAd.isChecked = it
    })
  }

}