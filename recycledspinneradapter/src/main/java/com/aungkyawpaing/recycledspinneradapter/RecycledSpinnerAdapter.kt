package com.aungkyawpaing.recycledspinneradapter

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.aungkyawpaing.recycledspinneradapter.RecycledSpinnerAdapter.ViewHolder

/**
 * Created by Vincent on 12/12/18
 */
abstract class RecycledSpinnerAdapter<T, VH : ViewHolder> : BaseAdapter {

  protected val itemList: MutableList<T>

  constructor() {
    itemList = mutableListOf()
  }

  constructor(itemList: List<T>) {
    this.itemList = itemList.toMutableList()
  }

  override fun getCount(): Int {
    return itemList.size
  }

  fun getItems(): List<T> {
    return this.itemList
  }

  override fun getItem(position: Int): T {
    return itemList[position]
  }

  override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
    return getCustomView(position, convertView, parent)
  }

  override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
    return getCustomView(position, convertView, parent)
  }

  private fun getCustomView(position: Int, convertView: View?, parent: ViewGroup?): View {

    var view = convertView

    val holder: VH

    if (convertView == null) {
      view = onCreateView(position, parent!!)
      holder = onCreateViewHolder(position, view)
      view.tag = holder
    } else {
      holder = view!!.tag as VH
    }

    onBindViewHolder(holder, position)

    return view
  }

  protected abstract fun onCreateView(position: Int, parent: ViewGroup): View

  protected abstract fun onCreateViewHolder(position: Int, view: View): VH

  protected abstract fun onBindViewHolder(holder: VH, position: Int)

  fun submitList(itemList: List<T>?) {
    this.itemList.clear()
    if (itemList != null) {
      this.itemList.addAll(itemList)
    }

    notifyDataSetChanged()
  }

  fun getPositionOfId(id: Long): Int {

    for (position in 0 until count) {
      if (getItemId(position) == id) {
        return position
      }
    }

    return -1
  }

  abstract class ViewHolder(view: View)

}

