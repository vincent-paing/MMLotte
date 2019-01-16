package com.mmlotte.lottery.data.network.retrofit

import com.mmlotte.lottery.data.network.entity.CheckSettingsResponse
import com.mmlotte.lottery.data.network.entity.CheckTicketResponse
import com.mmlotte.lottery.data.network.entity.LatestResultResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Vincent on 9/20/18
 */
interface MMLotteApi {

  @GET("latest")
  fun getLatestResult(): Call<LatestResultResponse>

  @GET("check")
  fun checkResult(@Query("number") ticketNumber: String): Call<CheckTicketResponse>

  @GET("inputsettings")
  fun getCheckSettings(): Call<CheckSettingsResponse>
}