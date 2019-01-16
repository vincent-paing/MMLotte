package com.mmlotte.lottery.feature.listing.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mmlotte.app.base.helper.AsyncViewResource
import com.mmlotte.lottery.domain.interactor.consent.GetConsentStatusForAd
import com.mmlotte.lottery.domain.interactor.result.GetLatestResult
import com.mmlotte.lottery.domain.model.LotteryResult
import com.mmlotte.lottery.feature.listing.model.ResultViewItem
import com.mmlotte.lottery.feature.listing.view.ResultListingView
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.threeten.bp.ZonedDateTime

/**
 * Created by Vincent on 12/7/18
 */
@RunWith(JUnit4::class)
class ResultListingViewModelTest {

  @Rule
  @JvmField
  var rule: TestRule = InstantTaskExecutorRule()

  @Mock lateinit var mockedGetLatestResult: GetLatestResult

  @Mock lateinit var mockedGetConsentStatusForAd: GetConsentStatusForAd

  lateinit var resultListingViewModel: ResultListingViewModel

  @Mock lateinit var resultListingView: ResultListingView

  private val resultViewItemMapper = MapperProvider.resultViewItemMapper()

  @Before
  fun setUp() {
    MockitoAnnotations.initMocks(this)
    resultListingViewModel =
        ResultListingViewModel(
          mockedGetLatestResult,
          mockedGetConsentStatusForAd,
          resultViewItemMapper
        )
    resultListingViewModel.attachView(resultListingView)
  }

  @Test
  fun testLoadingResult() {

    whenever(mockedGetLatestResult.execute(any())).thenReturn(Single.never<LotteryResult>())

    resultListingViewModel.getLatestResult()

    val expected = AsyncViewResource.loading<ResultViewItem>()
    val actual = resultListingViewModel.liveData.value

    Assert.assertEquals(expected, actual)
  }

  @Test
  fun testSuccessState() {
    val item = LotteryResult(
      resultDate = ZonedDateTime.now(),
      prizeList = listOf()
    )

    whenever(mockedGetLatestResult.execute(any())).thenReturn(
      Single.just(item)
    )

    resultListingViewModel.getLatestResult()

    val expected = AsyncViewResource.success(resultViewItemMapper.map(item))
    val actual = resultListingViewModel.liveData.value

    Assert.assertEquals(expected, actual)
  }

  @Test
  fun testErrorState() {
    val error = Throwable()

    whenever(mockedGetLatestResult.execute(any())).thenReturn(
      Single.error(error)
    )

    resultListingViewModel.getLatestResult()

    val expected = AsyncViewResource.error<Any>(error)
    val actual = resultListingViewModel.liveData.value

    Assert.assertEquals(expected, actual)
  }

  @After
  fun tearDown() {
    resultListingViewModel.detachView()

  }
}