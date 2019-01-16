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

package com.mmlotte.lottery.domain.interactor.consent

import com.mmlotte.lottery.domain.executor.PostExecutionThread
import com.mmlotte.lottery.domain.executor.ThreadExecutor
import com.mmlotte.lottery.domain.interactor.SingleUseCase
import com.mmlotte.lottery.domain.repository.ConsentRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Vincent on 8/20/18
 */
class GetHasAskedConsentForCrashLog @Inject constructor(
  private val consentRepository: ConsentRepository,
  postExecutionThread: PostExecutionThread,
  threadExecutor: ThreadExecutor
) : SingleUseCase<Boolean, Void>(postExecutionThread, threadExecutor) {

  override fun provideSingle(params: Void): Single<Boolean> {
    return consentRepository.getConsentStatusForLoggingCrash()
  }
}