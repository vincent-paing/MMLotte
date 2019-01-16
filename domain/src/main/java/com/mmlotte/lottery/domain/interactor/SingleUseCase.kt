/*
 * Copyright 2018 Aung Kyaw Paing  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed
 *  under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions
 *   and limitations under the License.
 */

package com.mmlotte.lottery.domain.interactor

import com.mmlotte.lottery.domain.executor.PostExecutionThread
import com.mmlotte.lottery.domain.executor.ThreadExecutor
import io.reactivex.Single
import io.reactivex.SingleTransformer
import io.reactivex.schedulers.Schedulers

abstract class SingleUseCase<T, Params>(
  private val postExecutionThread: PostExecutionThread, private val threadExecutor: ThreadExecutor
) {

  /**
   * Implement this method in your custom AsyncUseCase in order to provide the final [Single].
   *
   * @param params The Params.
   * @return The provided [Single].
   */
  abstract protected fun provideSingle(params: Params): Single<T>

  /**
   * Builds the provided [Single] and performs some transformation on it.
   *
   * @return The Observable with any transformation applied.
   */
  private fun buildUseCaseSingle(): SingleTransformer<T, T> {

    return SingleTransformer { single ->
      single.subscribeOn(Schedulers.from(threadExecutor)).observeOn(
        postExecutionThread.scheduler
      )
    }
  }

  /**
   * Executes the provided [Single]
   *
   * @param params The Params.
   * @return The provided Single.
   */
  fun execute(params: Params): Single<T> {
    val single = provideSingle(params)
    return single.compose(buildUseCaseSingle())
  }

}