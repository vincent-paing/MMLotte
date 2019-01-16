package com.mmlotte.lottery.feature.listing.viewmodel

import com.mmlotte.lottery.feature.listing.model.WinningNumberViewItem
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Created by Vincent on 12/7/18
 */
@RunWith(JUnit4::class)
class WinningNumberViewItemMapperTest {

  val winningNumberViewItemMapper = MapperProvider.winningNumberViewItemMapper()

  @Test
  fun testMapping() {

    val input = "ကစ971"

    val expected = WinningNumberViewItem(
      character = "ကစ",
      number = "၉၇၁"
    )

    val actual = winningNumberViewItemMapper.map(input)

    Assert.assertEquals(expected, actual)
  }
}
