package com.mmlotte.lottery.feature.check.view

import android.view.View
import android.view.ViewGroup
import com.aungkyawpaing.recycledspinneradapter.RecycledSpinnerAdapter
import com.mmlotte.app.base.helper.inflater
import com.mmlotte.lottery.feature.check.R
import com.mmlotte.lottery.feature.check.view.CharacterSpinnerAdapter.CharacterViewHolder
import kotlinx.android.synthetic.main.item_character.view.tvCharacter

/**
 * Created by Vincent on 12/12/18
 */
class CharacterSpinnerAdapter : RecycledSpinnerAdapter<String, CharacterViewHolder>() {

  override fun onCreateView(position: Int, parent: ViewGroup): View {
    return parent.inflater().inflate(R.layout.item_character, parent, false)
  }

  override fun onCreateViewHolder(position: Int, view: View): CharacterViewHolder {
    return CharacterViewHolder(view)
  }

  override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
    holder.tvCharacter.text = getItem(position)
  }

  override fun getItemId(position: Int): Long = position.toLong()

  inner class CharacterViewHolder(view: View) : RecycledSpinnerAdapter.ViewHolder(view) {

    internal val tvCharacter = view.tvCharacter
  }
}