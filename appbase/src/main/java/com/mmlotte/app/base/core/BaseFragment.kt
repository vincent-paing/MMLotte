package com.mmlotte.app.base.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment

/**
 * Created by Vincent on 12/6/18
 */
abstract class BaseFragment : Fragment() {

  @get:LayoutRes
  protected abstract val layoutId: Int

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
  ): android.view.View? {
    AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    val view = inflater.inflate(layoutId, container, false)
    return view
  }

}
