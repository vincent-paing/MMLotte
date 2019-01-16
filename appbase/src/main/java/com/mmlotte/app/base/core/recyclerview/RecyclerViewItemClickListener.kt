package com.mmlotte.app.base.core.recyclerview

import android.view.View

/**
 * Created by Vincent on 12/6/18
 */
interface RecyclerViewItemClickListener {

  fun onItemClick(view: View, position: Int)

  fun onItemLongClick(view: View, position: Int)
}

inline fun recyclerViewItemClickListener(
  crossinline onItemClick: ((View, Int) -> Unit) = { _, _ -> },
  crossinline onItemLongClick: ((View, Int) -> Unit) = { _, _ -> }
): RecyclerViewItemClickListener {
  return object : RecyclerViewItemClickListener {
    override fun onItemClick(view: View, position: Int) {
      onItemClick.invoke(view, position)
    }

    override fun onItemLongClick(view: View, position: Int) {
      onItemLongClick.invoke(view, position)
    }

  }
}

