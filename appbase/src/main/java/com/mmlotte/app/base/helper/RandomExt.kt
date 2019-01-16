package com.mmlotte.app.base.helper

import java.util.UUID
import kotlin.random.Random

/**
 * Created by Vincent on 12/7/18
 */

fun Random.nextString(): String {
  return UUID.randomUUID().toString()
}
