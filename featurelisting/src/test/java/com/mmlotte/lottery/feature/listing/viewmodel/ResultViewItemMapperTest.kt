package com.mmlotte.lottery.feature.listing.viewmodel

import com.mmlotte.lottery.domain.model.LotteryResult
import com.mmlotte.lottery.feature.listing.model.ResultViewItem
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime

/**
 * Created by Vincent on 12/7/18
 */
@RunWith(JUnit4::class)
class ResultViewItemMapperTest {

  val resultViewItemMapper = MapperProvider.resultViewItemMapper()

  @Test
  fun testMapping() {
    val input = LotteryResult(
      resultDate = ZonedDateTime.of(2018, 12, 7, 0, 0, 0, 0, ZoneId.of("Asia/Rangoon")),
      prizeList = listOf()
    )

    val expected = ResultViewItem(
      resultDate = "DECEMBER 2018",
      prizeList = listOf()
    )

    val actual = resultViewItemMapper.map(input)

    Assert.assertEquals(expected, actual)
  }
}
