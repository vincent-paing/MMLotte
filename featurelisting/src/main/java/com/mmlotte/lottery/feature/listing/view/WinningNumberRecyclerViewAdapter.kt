package com.mmlotte.lottery.feature.listing.view

import android.view.View
import android.view.ViewGroup
import com.mmlotte.app.base.core.recyclerview.BaseRecyclerViewAdapter
import com.mmlotte.app.base.core.recyclerview.BaseViewHolder
import com.mmlotte.app.base.core.recyclerview.RecyclerViewItemClickListener
import com.mmlotte.app.base.core.recyclerview.diffCallBackWith
import com.mmlotte.app.base.helper.inflater
import com.mmlotte.lottery.feature.listing.R
import com.mmlotte.lottery.feature.listing.model.WinningNumberViewItem
import com.mmlotte.lottery.feature.listing.view.WinningNumberRecyclerViewAdapter.WinningNumberViewHolder
import kotlinx.android.synthetic.main.item_winning_number.view.tvCharacter
import kotlinx.android.synthetic.main.item_winning_number.view.tvNumber

/**
 * Created by Vincent on 12/7/18
 */
class WinningNumberRecyclerViewAdapter :
  BaseRecyclerViewAdapter<WinningNumberViewItem, WinningNumberViewHolder>(
    diffCallBackWith(
      areItemTheSame = { oldItem, newItem ->
        oldItem == newItem
      },
      areContentsTheSame = { oldItem, newItem ->
        oldItem == newItem
      })
  ) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WinningNumberViewHolder {
    return WinningNumberViewHolder(
      parent.inflater().inflate(R.layout.item_winning_number, parent, false)
    )
  }

  inner class WinningNumberViewHolder(
    itemView: View,
    recyclerViewItemClickListener: RecyclerViewItemClickListener? = null
  ) : BaseViewHolder<WinningNumberViewItem>(itemView, recyclerViewItemClickListener) {

    val tvCharacter = itemView.tvCharacter
    val tvNumber = itemView.tvNumber

    override fun bind(item: WinningNumberViewItem) {
      tvCharacter.text = item.character
      tvNumber.text = item.number
    }

  }
}