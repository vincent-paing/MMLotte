package com.mmlotte.app.base.core.mvp

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.mmlotte.app.base.core.BaseFragment
import com.mmlotte.app.base.di.Injectable
import com.mmlotte.app.base.exception.InvalidMvpImplementationException
import com.mmlotte.lottery.domain.exception.GenericErrorMessageFactory
import javax.inject.Inject
import kotlin.reflect.KClass

/**
 * Created by Vincent on 12/6/18
 */
abstract class MvpFragment<V : Viewable, VM : BaseViewModel<V>> :
  BaseFragment(), Injectable {

  @Inject
  lateinit var viewModelFactory: ViewModelProvider.Factory

  @Inject
  lateinit var genericErrorMessageFactory: GenericErrorMessageFactory

  protected abstract val viewModel: VM

  override fun onCreate(savedInstanceState: Bundle?) {
    try {
      this as V
    } catch (exception: Exception) {
      throw InvalidMvpImplementationException()
    }
    super.onCreate(savedInstanceState)
  }

  override fun onViewCreated(view: android.view.View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    viewModel.attachView(this as V)
  }

  override fun onDestroyView() {
    viewModel.detachView()
    super.onDestroyView()
  }

  /**
   * Helper function for easily init of viewModel
   */
  protected inline fun <reified VM : BaseViewModel<V>> contractedViewModel(): Lazy<VM> =
    ViewModelLazy(VM::class)

  inner class ViewModelLazy<VM : androidx.lifecycle.ViewModel>(
    private val viewModelClass: KClass<VM>
  ) : Lazy<VM> {
    private var cached: VM? = null

    override val value: VM
      get() {
        var viewModel = cached
        if (viewModel == null) {
          viewModel =
              ViewModelProvider(
                this@MvpFragment,
                viewModelFactory
              ).get(viewModelClass.java)
          cached = viewModel
        }
        return viewModel
      }

    override fun isInitialized() = cached != null
  }

}