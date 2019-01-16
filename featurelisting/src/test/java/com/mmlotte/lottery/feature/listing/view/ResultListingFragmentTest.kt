package com.mmlotte.lottery.feature.listing.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mmlotte.app.base.helper.AsyncViewResource
import com.mmlotte.lottery.feature.listing.R
import com.mmlotte.lottery.feature.listing.model.ResultViewItem
import com.mmlotte.lottery.feature.listing.viewmodel.ResultListingViewModel
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.hamcrest.CoreMatchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations

/**
 * Created by Vincent on 12/7/18
 */
@RunWith(AndroidJUnit4::class)
class ResultListingFragmentTest {

  @Rule
  @JvmField
  var rule: TestRule = InstantTaskExecutorRule()

  @Mock lateinit var mockedViewModel: ResultListingViewModel

  @Mock lateinit var mockedFactory: ViewModelProvider.Factory

  val liveData = MutableLiveData<AsyncViewResource<ResultViewItem>>()

  @Before
  fun setUp() {
    MockitoAnnotations.initMocks(this)
    whenever(mockedFactory.create(ResultListingViewModel::class.java)).thenReturn(mockedViewModel)
  }

  @Test
  fun testLoadingState() {

    val scenario = launchFragmentInContainer(instantiate = {
      val fragment = ResultListingFragment()
      fragment.viewModelFactory = mockedFactory
      fragment
    })
    scenario.onFragment {
      it.subscribeToLiveData(liveData)
    }

    liveData.postValue(AsyncViewResource.loading())

    verify(mockedViewModel, times(1)).getLatestResult()

    onView(withId(R.id.tvDate)).check(matches(not(isDisplayed())))
    onView(withId(R.id.rvListing)).check(matches(not(isDisplayed())))
    onView(withId(R.id.progressBar)).check(matches(isDisplayed()))
  }

//  @Test
//  fun testSuccessState() {
//
//    val data = ResultViewItem(
//      resultDate = "DECEMBER 2018",
//      prizeList = listOf(
//        PrizeViewItem(
//          prizeId = 1,
//          prizeTitle = "Index 0",
//          winningNumberNumbers = listOf(
//            WinningNumberViewItem(
//              character = "က",
//              number = "၁၂၃၄၅၆"
//            )
//          )
//        )
//      )
//    )
//
//    val scenario = launchFragmentInContainer(instantiate = {
//      val fragment = ResultListingFragment()
//      fragment.viewModelFactory = mockedFactory
//      fragment
//    })
//    scenario.onFragment {
//      it.subscribeToLiveData(liveData)
//    }
//
//
//    liveData.postValue(AsyncViewResource.success(data))
//
//
//    verify(mockedViewModel, times(1)).getLatestResult()
//
//    onView(withId(R.id.progressBar)).check(matches(not(isDisplayed())))
//
//    onView(withId(R.id.tvDate)).check(matches(isDisplayed()))
//    onView(withId(R.id.tvDate)).check(matches(withText("DECEMBER 2018")))
//
//    onView(withId(R.id.rvListing)).check(matches(isDisplayed()))
//
//    onView(
//      RecyclerViewMatcher.withRecyclerView(R.id.rvListing).atPositionOnView(
//        0,
//        R.id.tvPrize
//      )
//    ).check(
//      matches(withText("Index 0"))
//    )
//
//  }
}