package com.mmlotte.lottery.domain.repository

import com.mmlotte.lottery.domain.model.CheckSettings
import com.mmlotte.lottery.domain.model.LotteryResult
import com.mmlotte.lottery.domain.model.Prize
import io.reactivex.Maybe
import io.reactivex.Single

/**
 * Created by Vincent on 12/6/18
 */
interface ResultRepository {

  fun getResult(): Single<LotteryResult>

  fun getSettingsForCheck(): Single<CheckSettings>

  fun checkForResult(ticketNumber: String): Maybe<Prize>

}