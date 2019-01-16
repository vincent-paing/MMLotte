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
import io.reactivex.Flowable
import io.reactivex.FlowableTransformer
import io.reactivex.schedulers.Schedulers

abstract class FlowableUseCase<T, Params>(
  private val postExecutionThread: PostExecutionThread, private val threadExecutor: ThreadExecutor
) {

  /**
   * Implement this method in your custom AsyncUseCase in order to provide the final [Flowable].
   *
   * @param params The Params.
   * @return The provided [Flowable].
   */
  abstract protected fun provideFlowable(params: Params): Flowable<T>

  /**
   * Builds the provided [Flowable] and performs some transformation on it.
   *
   * @return The Observable with any transformation applied.
   */
  private fun buildUseCaseFlowable(): FlowableTransformer<T, T> {

    return FlowableTransformer { flowable ->
      flowable.subscribeOn(Schedulers.from(threadExecutor)).observeOn(
        postExecutionThread.scheduler
      )
    }
  }

  /**
   * Executes the provided [Flowable]
   *
   * @param params The Params.
   * @return The provided Flowable.
   */
  fun execute(params: Params): Flowable<T> {
    val flowable = provideFlowable(params)
    return flowable.compose(buildUseCaseFlowable())
  }

}