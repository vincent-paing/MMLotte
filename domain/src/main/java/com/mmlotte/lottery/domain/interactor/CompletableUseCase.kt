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
import io.reactivex.Completable
import io.reactivex.CompletableTransformer
import io.reactivex.schedulers.Schedulers

abstract class CompletableUseCase<Params>(
  private val postExecutionThread: PostExecutionThread, private val threadExecutor: ThreadExecutor
) {

  /**
   * Implement this method in your custom AsyncUseCase in order to provide the final [Completable].
   *
   * @param params The Params.
   * @return The provided [Completable].
   */
  abstract protected fun provideCompletable(params: Params): Completable

  /**
   * Builds the provided [Completable] and performs some transformation on it.
   *
   * @return The Observable with any transformation applied.
   */
  private fun buildUseCaseCompletable(): CompletableTransformer {

    return CompletableTransformer { Completable ->
      Completable.subscribeOn(Schedulers.from(threadExecutor)).observeOn(
        postExecutionThread.scheduler
      )
    }
  }

  /**
   * Executes the provided [Completable]
   *
   * @param params The Params.
   * @return The provided Completable.
   */
  open fun execute(params: Params): Completable {
    val completable = provideCompletable(params)
    return completable.compose(buildUseCaseCompletable())
  }

}