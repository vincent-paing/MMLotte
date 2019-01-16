package com.mmlotte.app.base.core.mvp

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Vincent on 12/6/18
 */
abstract class BaseViewModel<viewable : Viewable> : ViewModel(), Presentable<viewable> {

  protected var view: viewable? = null

  protected val compositeDisposable: CompositeDisposable = CompositeDisposable()

  override fun attachView(viewable: viewable) {
    this.view = viewable
  }

  override fun detachView() {
    this.view = null
  }

  override fun onCleared() {
    compositeDisposable.dispose()
    super.onCleared()
  }

}