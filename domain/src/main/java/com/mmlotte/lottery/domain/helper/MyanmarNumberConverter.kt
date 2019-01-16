package com.mmlotte.lottery.domain.helper

/**
 * Created by Vincent on 12/7/18
 */
object MyanmarNumberConverter {

  const val englishNumberPattern = "[0-9]"
  const val burmeseNumberPattern = "[၀-၉]"

  /**
   * Transform Burmese number to English number (e.g "၃၀" -> "30")
   *
   * @param n Burmese Number in String type
   * @return English Number in String type
   */
  fun getEngNumber(n: String): String {
    val eng = StringBuilder(n.length)
    for (c in n.toCharArray()) {
      if (Regex(burmeseNumberPattern).matches(c.toString())) {
        eng.append((c.toInt() - 4112).toChar())
      } else {
        eng.append(c)
      }
    }
    return eng.toString()
  }

  /**
   * Transform English number to Burmese number (e.g "30" -> "၃၀");
   *
   * @param n English Number in String type.
   * @return Burmese Number in String type.
   */
  fun getMMNumber(n: String): String {
    val mm = StringBuilder(n.length)
    for (c in n.toCharArray()) {
      if (Regex(englishNumberPattern).matches(c.toString())) {
        mm.append((c.toInt() + 4112).toChar())
      } else {
        mm.append(c)
      }
    }
    return mm.toString()
  }

  /**
   * Transform English number to Burmese number (e.g 30 -> "၃၀")
   *
   * @param n English Number in int type
   * @return Burmese Number in String type
   */
  fun getMMNumber(n: Int): String {
    return getMMNumber(n.toString())
  }
}
