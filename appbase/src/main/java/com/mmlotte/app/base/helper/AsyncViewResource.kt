package com.mmlotte.app.base.helper

import timber.log.Timber

/**
 * Created by Vincent on 12/6/18
 */
data class AsyncViewResource<out T> constructor(
  private val status: ResourceState,
  private val data: T? = null,
  private val error: Throwable? = null,
  private val retryable: Boolean? = true
) {

  private val onSuccessStub: (T) -> Unit = {}
  private val onErrorStub: (Throwable, Boolean) -> Unit = { _, _ -> }
  private val onLoadingStub: () -> Unit = {}

  companion object {

    fun <T> success(data: T): AsyncViewResource<T> {
      return AsyncViewResource(ResourceState.SUCCESS, data, null)
    }

    fun <T> error(error: Throwable?, retryable: Boolean? = true): AsyncViewResource<T> {
      return AsyncViewResource(ResourceState.ERROR, null, error, retryable)
    }

    fun <T> loading(): AsyncViewResource<T> {
      return AsyncViewResource(ResourceState.LOADING, null, null)
    }
  }

  fun subscribeState(
    onLoading: () -> Unit = onLoadingStub,
    onError: (Throwable, Boolean) -> Unit = onErrorStub,
    onSuccess: (T) -> Unit = onSuccessStub
  ) {
    when (this.status) {
      ResourceState.LOADING -> onLoading.invoke()
      ResourceState.SUCCESS -> onSuccess.invoke(data!!)
      ResourceState.ERROR -> {
        Timber.e(error)
        onError(error!!, retryable!!)
      }
    }
  }
}

enum class ResourceState {
  LOADING,
  SUCCESS,
  ERROR
}
