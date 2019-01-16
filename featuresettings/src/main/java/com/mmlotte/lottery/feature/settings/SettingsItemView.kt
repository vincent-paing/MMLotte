package com.mmlotte.lottery.feature.settings

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.view_settings_item.view.ivIcon
import kotlinx.android.synthetic.main.view_settings_item.view.tvSubtitle
import kotlinx.android.synthetic.main.view_settings_item.view.tvTitle

/**
 * Created by Vincent on 8/20/18
 */
class SettingsItemView : FrameLayout {

  var title: CharSequence = ""
  var subTitle: CharSequence? = null
  var icon: Drawable? = null

  constructor(context: Context) : super(context) {
    init(context, null)
  }

  constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
    init(context, attrs)
  }

  constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
    context,
    attrs,
    defStyleAttr
  ) {
    init(context, attrs)
  }

  private fun init(context: Context, attrs: AttributeSet?) {
    LayoutInflater.from(context).inflate(R.layout.view_settings_item, this, true)


    if (attrs != null) {
      val typedArr = context.obtainStyledAttributes(attrs, R.styleable.SettingsItemView)

      icon = typedArr.getDrawable(R.styleable.SettingsItemView_icon)
      val titleStr = typedArr.getString(R.styleable.SettingsItemView_settingsTitle)
      title = titleStr ?: ""
      subTitle = typedArr.getString(R.styleable.SettingsItemView_settingsSubTitle)

      typedArr.recycle()
    }
  }

  override fun onFinishInflate() {
    super.onFinishInflate()

    tvTitle.text = title

    if (icon == null) {
      ivIcon.visibility = View.GONE
    } else {
      ivIcon.setImageDrawable(icon)
    }

    if (subTitle == null) {
      tvSubtitle.visibility = View.GONE
    } else {
      tvSubtitle.text = subTitle
    }
  }
}
