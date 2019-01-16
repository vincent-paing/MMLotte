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

import androidx.lifecycle.MutableLiveData
import com.mmlotte.app.base.core.mvp.BaseViewModel
import com.mmlotte.lottery.domain.interactor.consent.GetConsentStatusForAd
import com.mmlotte.lottery.domain.interactor.consent.GetConsentStatusForCrashLog
import com.mmlotte.lottery.domain.interactor.consent.UpdateConsentStatusForAd
import com.mmlotte.lottery.domain.interactor.consent.UpdateConsentStatusForCrashLog
import io.reactivex.rxkotlin.Singles
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

/**
 * Created by Vincent on 8/20/18
 */
class ConsentSettingsViewModel @Inject constructor(
  private val getConsentStatusForAd: GetConsentStatusForAd,
  private val getConsentStatusForCrashLog: GetConsentStatusForCrashLog,
  private val updateConsentStatusForAd: UpdateConsentStatusForAd,
  private val updateConsentStatusForCrashLog: UpdateConsentStatusForCrashLog
) : BaseViewModel<ConsentSettingsView>() {

  private val crashLogConsentLiveData = MutableLiveData<Boolean>()
  private val adConsentLiveData = MutableLiveData<Boolean>()

  override fun attachView(viewable: ConsentSettingsView) {
    super.attachView(viewable)
    view?.subscribeToCrashLogConsentLiveData(crashLogConsentLiveData)
    view?.subscribeToAdConsentLiveData(adConsentLiveData)
  }

  fun getConsentStatus() {
    Singles.zip(
      getConsentStatusForCrashLog.execute(Unit),
      getConsentStatusForAd.execute(Unit)
    ).subscribeBy {
      crashLogConsentLiveData.postValue(it.first)
      adConsentLiveData.postValue(it.second)
    }.addTo(compositeDisposable)
  }

  fun toggleConsentStatusForCrashLog() {
    getConsentStatusForCrashLog.execute(Unit).flatMapCompletable {
      updateConsentStatusForCrashLog.execute(!it)
    }.andThen(getConsentStatusForCrashLog.execute(Unit)).subscribeBy {
      crashLogConsentLiveData.postValue(it)
    }.addTo(compositeDisposable)
  }

  fun toggleConsentStatusForAd() {
    getConsentStatusForAd.execute(Unit).flatMapCompletable {
      updateConsentStatusForAd.execute(!it)
    }.andThen(getConsentStatusForAd.execute(Unit)).subscribeBy {
      adConsentLiveData.postValue(it)
    }.addTo(compositeDisposable)
  }

}