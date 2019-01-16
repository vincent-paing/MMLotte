package com.mmlotte.app.base.core.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Vincent on 12/6/18
 */
abstract class BaseViewHolder<itemType> protected constructor(
  itemView: View,
  val recyclerViewItemClickListener: RecyclerViewItemClickListener? = null
) : RecyclerView.ViewHolder(itemView) {

  abstract fun bind(item: itemType)
}