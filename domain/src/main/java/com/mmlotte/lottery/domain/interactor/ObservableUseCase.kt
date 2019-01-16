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
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.schedulers.Schedulers

abstract class ObservableUseCase<T, Params>(
  private val postExecutionThread: PostExecutionThread, private val threadExecutor: ThreadExecutor
) {

  /**
   * Implement this method in your custom AsyncUseCase in order to provide the final [Observable].
   *
   * @param params The Params.
   * @return The provided [Observable].
   */
  protected abstract fun provideObservable(params: Params): Observable<T>

  /**
   * Builds the provided [Observable] and performs some transformation on it.
   *
   * @return The Observable with any transformation applied.
   */
  private fun buildUseCaseObservable(): ObservableTransformer<T, T> {

    return ObservableTransformer { observable ->
      observable.subscribeOn(Schedulers.from(threadExecutor)).observeOn(
        postExecutionThread.scheduler
      )
    }
  }

  /**
   * Execute the provided [Observable].
   *
   * This is a handful method that allow you, for instance, to chain more Observables or apply a
   * map.
   *
   * @param params The Params.
   * @return The provided Observable.
   */
  fun execute(params: Params): Observable<T> {
    val ob = provideObservable(params)
    return ob.compose(buildUseCaseObservable())
  }

}