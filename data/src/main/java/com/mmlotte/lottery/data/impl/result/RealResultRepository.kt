package com.mmlotte.lottery.data.impl.result

import com.mmlotte.lottery.data.datasource.network.ResultNetworkDataSource
import com.mmlotte.lottery.data.mapper.CheckSettingsEntityMapper
import com.mmlotte.lottery.data.mapper.PrizeEntityMapper
import com.mmlotte.lottery.data.mapper.ResultListingEntityMapper
import com.mmlotte.lottery.domain.model.CheckSettings
import com.mmlotte.lottery.domain.model.LotteryResult
import com.mmlotte.lottery.domain.model.Prize
import com.mmlotte.lottery.domain.repository.ResultRepository
import io.reactivex.Maybe
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Vincent on 12/6/18
 */
class RealResultRepository @Inject constructor(
  private val resultNetworkDataSource: ResultNetworkDataSource,
  private val resultListingEntityMapper: ResultListingEntityMapper,
  private val prizeEntityMapper: PrizeEntityMapper,
  private val checkSettingsEntityMapper: CheckSettingsEntityMapper
) : ResultRepository {

  override fun getSettingsForCheck(): Single<CheckSettings> {
    return Single.fromCallable {
      resultNetworkDataSource.getSettingsForCheck()
    }.map(checkSettingsEntityMapper::map)
  }

  override fun getResult(): Single<LotteryResult> {
    return Single.fromCallable {
      resultNetworkDataSource.getResult()
    }.map(resultListingEntityMapper::map)
  }

  override fun checkForResult(ticketNumber: String): Maybe<Prize> {
    return Maybe.create {

      val result = resultNetworkDataSource.checkNumber(ticketNumber)

      if (result.prize != null) {
        it.onSuccess(prizeEntityMapper.map(result.prize))
      }

      it.onComplete()
    }
  }

}