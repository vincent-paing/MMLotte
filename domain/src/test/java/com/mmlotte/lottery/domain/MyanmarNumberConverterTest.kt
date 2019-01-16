package com.mmlotte.lottery.domain

import com.mmlotte.lottery.domain.helper.MyanmarNumberConverter
import junit.framework.TestCase
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Created by Vincent on 12/11/18
 */
@RunWith(JUnit4::class)
class MyanmarNumberConverterTest {

  @Test
  fun testEnglishNumber() {
    val input = "က123456"

    val expected = "က၁၂၃၄၅၆"

    val actual = MyanmarNumberConverter.getMMNumber(input)

    TestCase.assertEquals(expected, actual)
  }

  @Test
  fun testBurmeseAndEnglishMixed() {
    val input = "က12၃4၅6"

    val expected = "က၁၂၃၄၅၆"

    val actual = MyanmarNumberConverter.getMMNumber(input)

    TestCase.assertEquals(expected, actual)
  }

  @Test
  fun testBurmeseOnly() {
    val input = "က၁၂၃၄၅၆"

    val expected = "က၁၂၃၄၅၆"

    val actual = MyanmarNumberConverter.getMMNumber(input)

    TestCase.assertEquals(expected, actual)
  }
}

