package com.mmlotte.lottery.domain.interactor.result

import com.mmlotte.lottery.domain.helper.TestExecutor
import com.mmlotte.lottery.domain.helper.TestThread
import com.mmlotte.lottery.domain.repository.ResultRepository
import com.nhaarman.mockito_kotlin.argumentCaptor
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations

/**
 * Created by Vincent on 12/11/18
 */
@RunWith(JUnit4::class)
class CheckLotteryTest {

  @Mock lateinit var mockedResultRepository: ResultRepository

  val repositoryInputCaptor = argumentCaptor<String>()

  lateinit var checkLottery: CheckLottery

  @Before
  fun setUp() {
    MockitoAnnotations.initMocks(this)

    checkLottery = CheckLottery(mockedResultRepository, TestThread(), TestExecutor())
  }

  @Test
  fun testEnglishNumber() {
    val input = CheckLottery.Params("က", "၁၂၃၄၅၆")

    val expected = CheckLottery.Params("က", "123456")

    val actual = checkLottery.normalizeInputValue(input)

    assertEquals(expected, actual)
  }

  @Test
  fun testBurmeseAndEnglishMixed() {

    val input = CheckLottery.Params("က", "12၃4၅6")

    val expected = CheckLottery.Params("က", "123456")

    val actual = checkLottery.normalizeInputValue(input)

    assertEquals(expected, actual)
  }
}