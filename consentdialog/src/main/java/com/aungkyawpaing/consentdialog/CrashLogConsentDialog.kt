/*
 * Copyright 2018 Aung Kyaw Paing  Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions
 * and limitations under the License.
 */

package com.aungkyawpaing.consentdialog

import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.dialog_crash_log.btnNo
import kotlinx.android.synthetic.main.dialog_crash_log.btnYes
import kotlinx.android.synthetic.main.dialog_crash_log.tvPrivacyPolicy

/**
 * Created by Vincent on 8/20/18
 */
class CrashLogConsentDialog : DialogFragment() {

  companion object {

    private val ARG_PRIVACY_POLICY = "PRIVACY_POLICY"

    fun newInstance(consentListener: ConsentListener, privacyPolicyUrl: String? = null)
        : CrashLogConsentDialog {
      val fragment = CrashLogConsentDialog()
      fragment.consentListener = consentListener
      if (privacyPolicyUrl != null) {
        val bundle = Bundle()
        bundle.putString(CrashLogConsentDialog.ARG_PRIVACY_POLICY, privacyPolicyUrl)
        fragment.arguments = bundle
      }
      return fragment
    }

  }

  interface ConsentListener {
    fun onConsent(hasConsented: Boolean)
  }

  var consentListener: ConsentListener? = null
  val privacyPolicyUrl: String? by lazy {
    arguments?.getString(ARG_PRIVACY_POLICY, "")
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val rootView = inflater.inflate(R.layout.dialog_crash_log, container, false)
    return rootView!!
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    btnYes.setOnClickListener {
      consentListener?.onConsent(true)
      dismiss()
    }

    btnNo.setOnClickListener {
      consentListener?.onConsent(false)
      dismiss()
    }

    if (privacyPolicyUrl != null) {
      tvPrivacyPolicy.setOnClickListener {
        val intent = Intent()
        intent.action = Intent.ACTION_VIEW
        intent.data = Uri.parse(privacyPolicyUrl!!)
        startActivity(intent)
      }
    } else {
      tvPrivacyPolicy.visibility = View.GONE
    }

  }

  override fun onCancel(dialog: DialogInterface) {
    super.onCancel(dialog)
    consentListener?.onConsent(true)
  }
}