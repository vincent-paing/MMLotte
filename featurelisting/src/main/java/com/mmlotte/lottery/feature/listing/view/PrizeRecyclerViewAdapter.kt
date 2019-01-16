package com.mmlotte.lottery.feature.listing.view

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mmlotte.app.base.core.recyclerview.BaseRecyclerViewAdapter
import com.mmlotte.app.base.core.recyclerview.BaseViewHolder
import com.mmlotte.app.base.core.recyclerview.RecyclerViewItemClickListener
import com.mmlotte.app.base.core.recyclerview.diffCallBackWith
import com.mmlotte.app.base.helper.inflater
import com.mmlotte.lottery.feature.listing.R
import com.mmlotte.lottery.feature.listing.model.PrizeViewItem
import com.mmlotte.lottery.feature.listing.view.PrizeRecyclerViewAdapter.PrizeViewHolder
import kotlinx.android.synthetic.main.item_prize.view.rvWinningNumbers
import kotlinx.android.synthetic.main.item_prize.view.tvPrize

/**
 * Created by Vincent on 12/7/18
 */
class PrizeRecyclerViewAdapter : BaseRecyclerViewAdapter<PrizeViewItem, PrizeViewHolder>(
  diffCallBackWith(
    areItemTheSame = { oldItem, newItem ->
      oldItem.prizeId == newItem.prizeId
    },
    areContentsTheSame = { oldItem, newItem ->
      oldItem == newItem
    }
  )) {

  private val viewPool = RecyclerView.RecycledViewPool()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrizeViewHolder {
    val holder = PrizeViewHolder(
      parent.inflater().inflate(R.layout.item_prize, parent, false),
      recyclerViewItemClickListener
    )
    holder.rvWinningNumber.setRecycledViewPool(viewPool)
    return holder
  }

  inner class PrizeViewHolder(
    itemView: View,
    recyclerViewItemClickListener: RecyclerViewItemClickListener?
  ) : BaseViewHolder<PrizeViewItem>(itemView, recyclerViewItemClickListener) {

    val tvPrize = itemView.tvPrize
    val rvWinningNumber = itemView.rvWinningNumbers
    val winningNumberRecyclerViewAdapter = WinningNumberRecyclerViewAdapter()

    init {
      rvWinningNumber.layoutManager =
          GridLayoutManager(itemView.context, 2, RecyclerView.VERTICAL, false)
      rvWinningNumber.addItemDecoration(
        DividerItemDecoration(
          itemView.context,
          RecyclerView.VERTICAL
        )
      )
      rvWinningNumber.adapter = winningNumberRecyclerViewAdapter
      rvWinningNumber.isNestedScrollingEnabled = false
    }

    override fun bind(item: PrizeViewItem) {
      tvPrize.text = item.prizeTitle
      winningNumberRecyclerViewAdapter.submitList(item.winningNumberNumbers)
    }

  }

}