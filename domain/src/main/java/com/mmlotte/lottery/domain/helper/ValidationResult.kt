package com.mmlotte.lottery.domain.helper

/**
 * Created by Vincent on 12/12/18
 */
sealed class ValidationResult {
  object Valid : ValidationResult()
  data class Invalid(val throwable: Throwable) : ValidationResult()
}
