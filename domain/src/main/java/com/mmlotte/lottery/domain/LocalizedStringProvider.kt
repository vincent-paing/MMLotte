package com.mmlotte.lottery.domain

import java.util.Locale

/**
 * Created by Vincent on 9/20/18
 */
interface LocalizedStringProvider {

  fun getString(locale: Locale)
}