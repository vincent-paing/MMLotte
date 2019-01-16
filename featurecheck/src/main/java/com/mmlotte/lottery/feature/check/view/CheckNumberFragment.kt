package com.mmlotte.lottery.feature.check.view

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.google.ads.mediation.admob.AdMobAdapter
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.material.snackbar.Snackbar
import com.mmlotte.app.base.core.mvp.MvpFragment
import com.mmlotte.app.base.helper.AsyncViewResource
import com.mmlotte.app.base.helper.setVisible
import com.mmlotte.app.base.helper.showShortToast
import com.mmlotte.lottery.domain.exception.IllegalLotteryNumberInputException
import com.mmlotte.lottery.domain.exception.LotteryNumberInputLengthException
import com.mmlotte.lottery.domain.model.Prize
import com.mmlotte.lottery.feature.check.R
import com.mmlotte.lottery.feature.check.R.color
import com.mmlotte.lottery.feature.check.R.string
import com.mmlotte.lottery.feature.check.model.CheckSettingsViewItem
import com.mmlotte.lottery.feature.check.viewmodel.CheckNumberViewModel
import com.squareup.phrase.Phrase
import kotlinx.android.synthetic.main.fragment_check_number.btnCheck
import kotlinx.android.synthetic.main.fragment_check_number.editTextLotteryNumber
import kotlinx.android.synthetic.main.fragment_check_number.progressBar
import kotlinx.android.synthetic.main.fragment_check_number.spinnerCharacter
import kotlinx.android.synthetic.main.fragment_check_number.textInputLottery
import kotlinx.android.synthetic.main.fragment_check_number.tvDate
import kotlinx.android.synthetic.main.fragment_check_number.tvResult
import timber.log.Timber

/**
 * Created by Vincent on 12/11/18
 */
class CheckNumberFragment : MvpFragment<CheckNumberView, CheckNumberViewModel>(),
  CheckNumberView {

  override val viewModel: CheckNumberViewModel by contractedViewModel()

  override val layoutId: Int
    get() = R.layout.fragment_check_number

  val characterSpinnerAdapter = CharacterSpinnerAdapter()

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    editTextLotteryNumber.setOnFocusChangeListener { v, hasFocus ->
      if (hasFocus) {
        val inputMethodManager =
          requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        inputMethodManager?.showSoftInput(editTextLotteryNumber, InputMethodManager.SHOW_IMPLICIT)
      } else {
        val inputMethodManager =
          requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        inputMethodManager?.hideSoftInputFromWindow(v.windowToken, 0)
      }
    }
    editTextLotteryNumber.requestFocus()

    spinnerCharacter.adapter = characterSpinnerAdapter

    btnCheck.setOnClickListener {
      textInputLottery.error = null
      viewModel.checkNumberForResult(
        characterSpinnerAdapter.getItem(spinnerCharacter.selectedItemPosition),
        editTextLotteryNumber.text.toString()
      )
    }
    interstitialAd.adUnitId = getString(R.string.interstitial_ad_id)

    if (savedInstanceState == null) {
      viewModel.getSettings()
      viewModel.getConsentStatusForAd()
    }
  }

  //region Loading stuffs
  override fun subscribeToSettingsLiveData(liveData: LiveData<AsyncViewResource<CheckSettingsViewItem>>) {
    liveData.observe(viewLifecycleOwner, Observer { viewResource ->
      viewResource?.subscribeState(
        onLoading = {
          tvDate.setVisible(false)
          showCheckLoading()
        },
        onSuccess = {
          hideLoading()
          tvDate.setVisible(true)
          tvDate.text = it.dateString
          characterSpinnerAdapter.submitList(it.characters)
        },
        onError = { error, _ ->
          tvDate.setVisible(false)
          tvResult.setVisible(false)
          btnCheck.setVisible(false)
          progressBar.setVisible(false)
          spinnerCharacter.isEnabled = false
          editTextLotteryNumber.isFocusableInTouchMode = false
          editTextLotteryNumber.isFocusable = false
          editTextLotteryNumber.isEnabled = false
          val errorString = genericErrorMessageFactory.getErrorMessage(error)
          showErrorSnackBar(errorString)
        }
      )

    })
  }

  override fun showCheckLoading() {
    tvResult.setVisible(false)
    btnCheck.setVisible(false)
    progressBar.setVisible(true)
    spinnerCharacter.isEnabled = false
    editTextLotteryNumber.isFocusableInTouchMode = false
    editTextLotteryNumber.isFocusable = false
    editTextLotteryNumber.isEnabled = false
  }

  override fun showNoResult() {
    hideLoading()
    tvResult.setTextColor(
      ContextCompat.getColor(
        requireContext(),
        color.red
      )
    )
    tvResult.setText(string.lose_text)
  }

  override fun showResult(prize: Prize) {
    hideLoading()
    val prizeString = Phrase.from(
      requireContext(),
      string.winning_text
    )
      .put("prize", prize.prizeTitle)
      .format()

    tvResult.setTextColor(
      ContextCompat.getColor(
        requireContext(),
        color.green
      )
    )
    tvResult.text = prizeString
  }

  internal fun hideLoading() {
    tvResult.setVisible(true)
    btnCheck.setVisible(true)
    progressBar.setVisible(false)
    spinnerCharacter.isEnabled = true
    editTextLotteryNumber.isFocusableInTouchMode = true
    editTextLotteryNumber.isFocusable = true
    editTextLotteryNumber.isEnabled = true
  }

  override fun showInputError(it: IllegalLotteryNumberInputException) {
    hideLoading()
    val errorString = if (it is LotteryNumberInputLengthException) {
      getString(R.string.error_invalid_length)
    } else {
      "Error"
    }
    textInputLottery.error = errorString
  }

  override fun showCheckError(error: Throwable) {
    hideLoading()
    showShortToast(error.message ?: "Error")
  }

  fun showErrorSnackBar(message: CharSequence) {
    val snackBar = Snackbar.make(
      this.view!!,
      message,
      Snackbar.LENGTH_INDEFINITE
    )
    snackBar.setAction(R.string.retry) {
      viewModel.getSettings()
    }
    snackBar.addCallback(object : Snackbar.Callback() {
      override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
        if (event == DISMISS_EVENT_SWIPE) {
          viewModel.getSettings()
        }
      }
    })
    snackBar.show()
  }

  //endregion

  //region Ad

  private val interstitialAd by lazy {
    InterstitialAd(requireContext())
  }

  override fun preLoadAd(allowPersonalized: Boolean) {
    val adRequestBuilder = AdRequest.Builder().addTestDevice("7B9E4725EE7314556494B2D85A32E3CF")

    if (!allowPersonalized) {
      val networkExtraBundle = bundleOf(
        "npa" to 1
      )

      adRequestBuilder.addNetworkExtrasBundle(
        AdMobAdapter::class.java, networkExtraBundle
      )
    }

    interstitialAd.adListener = object : AdListener() {
      override fun onAdFailedToLoad(p0: Int) {
        super.onAdFailedToLoad(p0)
        Timber.e("Ad failed to load with code $p0")
      }

    }
    interstitialAd.loadAd(adRequestBuilder.build())
  }

  override fun showAd() {
    if (interstitialAd.isLoaded) {
      interstitialAd.show()
    }
  }

  //endregion
}