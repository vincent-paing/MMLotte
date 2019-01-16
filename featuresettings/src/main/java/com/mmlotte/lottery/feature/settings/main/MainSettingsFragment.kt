package com.mmlotte.lottery.feature.settings.main

import android.os.Bundle
import android.view.View
import android.webkit.WebView
import androidx.appcompat.app.AlertDialog
import com.jakewharton.rxbinding3.view.clicks
import com.mmlotte.app.base.core.mvp.MvpFragment
import com.mmlotte.app.base.helper.showShortToast
import com.mmlotte.lottery.domain.executor.PostExecutionThread
import com.mmlotte.lottery.feature.settings.BuildConfig
import com.mmlotte.lottery.feature.settings.R
import com.mmlotte.lottery.feature.settings.consent.ConsentSettingsActivity
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.fragment_main_settings.tvConsent
import kotlinx.android.synthetic.main.fragment_main_settings.tvLicense
import kotlinx.android.synthetic.main.fragment_main_settings.tvPrivacy
import kotlinx.android.synthetic.main.fragment_main_settings.tvVersion
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by Vincent on 12/14/18
 */
class MainSettingsFragment : MvpFragment<MainSettingsView, MainSettingsViewModel>(),
  MainSettingsView {

  override val viewModel: MainSettingsViewModel by contractedViewModel()

  override val layoutId: Int = R.layout.fragment_main_settings

  @Inject lateinit var postExecutionThread: PostExecutionThread

  var versionClickDisposable: Disposable? = null

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    tvPrivacy.setOnClickListener {
      showPrivacyPolicy()
    }

    tvLicense.setOnClickListener {
      showLicense()
    }

    tvConsent.setOnClickListener {
      showConsentSettings()
    }

    showBuildVersion()

    versionClickDisposable = tvVersion.clicks()
      .buffer(500, TimeUnit.MILLISECONDS)
      .map {
        Timber.i("EASTER EGG: ${it.size}")
        it.size
      }.filter {
        it >= 3
      }
      .observeOn(postExecutionThread.scheduler)
      .subscribeBy(
        onNext = {
          showShortToast("dQw4w9WgXcQ")
        },
        onError = {
          Timber.e(it)
        }
      )

  }

  override fun showPrivacyPolicy() {
    val builder = AlertDialog.Builder(requireActivity())
    val webView = WebView(requireActivity())
    webView.loadUrl(requireContext().getString(R.string.privacy_policy_url))

    builder.setView(webView).setPositiveButton(android.R.string.ok, null).show()
  }

  override fun showTermsAndConditions() {

  }

  override fun showLicense() {
    val builder = AlertDialog.Builder(requireActivity())
    val webView = WebView(requireActivity())
    webView.loadUrl("file:///android_asset/license.html")

    builder.setView(webView).setPositiveButton(android.R.string.ok, null).show()
  }

  override fun showConsentSettings() {
    val intent = ConsentSettingsActivity.newIntent(requireContext())
    startActivity(intent)
  }

  override fun showBuildVersion() {
    tvVersion.text = "Version : ${BuildConfig.VERSION_NAME}"
  }

  override fun onDestroyView() {
    versionClickDisposable?.dispose()
    super.onDestroyView()
  }

}
