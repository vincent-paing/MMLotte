package com.mmlotte.lottery.feature.listing.view

import android.os.Bundle
import android.util.TypedValue
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.ads.mediation.admob.AdMobAdapter
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.material.snackbar.Snackbar
import com.mmlotte.app.base.core.mvp.MvpFragment
import com.mmlotte.app.base.helper.AsyncViewResource
import com.mmlotte.app.base.helper.setVisible
import com.mmlotte.lottery.feature.listing.R
import com.mmlotte.lottery.feature.listing.model.ResultViewItem
import com.mmlotte.lottery.feature.listing.viewmodel.ResultListingViewModel
import kotlinx.android.synthetic.main.fragment_result_listing.adView
import kotlinx.android.synthetic.main.fragment_result_listing.progressBar
import kotlinx.android.synthetic.main.fragment_result_listing.rvListing
import kotlinx.android.synthetic.main.fragment_result_listing.tvDate
import timber.log.Timber

/**
 * Created by Vincent on 12/7/18
 */
class ResultListingFragment : MvpFragment<ResultListingView, ResultListingViewModel>(),
  ResultListingView {

  override val viewModel: ResultListingViewModel by contractedViewModel()

  override val layoutId: Int = R.layout.fragment_result_listing

  private val prizeRecyclerViewAdapter = PrizeRecyclerViewAdapter()

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    rvListing.adapter = prizeRecyclerViewAdapter
    rvListing.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)


    if (savedInstanceState == null) {
      viewModel.getLatestResult()
      viewModel.getConsentStatusForAd()
    }
  }

  override fun subscribeToLiveData(liveData: LiveData<AsyncViewResource<ResultViewItem>>) {
    liveData.observe(viewLifecycleOwner, Observer { viewResource ->
      viewResource.subscribeState(
        onLoading = {
          progressBar.setVisible(true)
          rvListing.setVisible(false)
          tvDate.setVisible(false)
        },
        onSuccess = {
          progressBar.setVisible(false)
          rvListing.setVisible(true)
          tvDate.setVisible(true)
          tvDate.text = it.resultDate
          prizeRecyclerViewAdapter.submitList(it.prizeList)
        }, onError = { error, _ ->
          progressBar.setVisible(false)
          rvListing.setVisible(false)
          tvDate.setVisible(false)
          val errorString = genericErrorMessageFactory.getErrorMessage(error)
          showErrorSnackBar(errorString)
        }
      )
    })
  }

  fun showErrorSnackBar(message: CharSequence) {

    val snackBar = Snackbar.make(
      this.view!!,
      message,
      Snackbar.LENGTH_INDEFINITE
    )
    snackBar.setAction(R.string.retry) {
      viewModel.getLatestResult()
    }
    snackBar.addCallback(object : Snackbar.Callback() {
      override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
        if (event == DISMISS_EVENT_SWIPE) {
          viewModel.getLatestResult()
        }
      }
    })

    //Because of STUPID FUCKING BROKEN AF COORDINATOR LAYOUT LIKE FUCKKK WHY IS IT LIKE THAT
    val bottomMargin = Math.round(
      TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, 56f, resources.displayMetrics
      )
    )

//    snackBar.apply {
//      this.
//    }
    snackBar.show()
  }

  override fun showAd(allowPersonalized: Boolean) {
    val adRequestBuilder = AdRequest.Builder().addTestDevice("7B9E4725EE7314556494B2D85A32E3CF")

    if (!allowPersonalized) {
      val networkExtraBundle = bundleOf(
        "npa" to 1
      )

      adRequestBuilder.addNetworkExtrasBundle(
        AdMobAdapter::class.java, networkExtraBundle
      )
    }

    adView.adListener = object : AdListener() {
      override fun onAdFailedToLoad(p0: Int) {
        Timber.e("Ad load error $p0")
        if (this@ResultListingFragment.isAdded) {
          adView.setVisible(false)
        }
        super.onAdFailedToLoad(p0)
      }
    }
    adView.loadAd(adRequestBuilder.build())
  }

}