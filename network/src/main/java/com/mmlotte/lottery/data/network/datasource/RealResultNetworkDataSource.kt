package com.mmlotte.lottery.data.network.datasource

import com.mmlotte.lottery.data.datasource.network.ResultNetworkDataSource
import com.mmlotte.lottery.data.entity.CheckLotterySettingsEntity
import com.mmlotte.lottery.data.entity.ResultEntity
import com.mmlotte.lottery.data.entity.ResultListingEntity
import com.mmlotte.lottery.data.network.mapper.CheckSettingsResponseMapper
import com.mmlotte.lottery.data.network.mapper.CheckTicketResponseMapper
import com.mmlotte.lottery.data.network.mapper.LatestResultResponseMapper
import com.mmlotte.lottery.data.network.retrofit.MMLotteApi
import javax.inject.Inject

/**
 * Created by Vincent on 9/20/18
 */
class RealResultNetworkDataSource @Inject constructor(
  private val mmLotteApi: MMLotteApi,
  private val latestResultResponseMapper: LatestResultResponseMapper,
  private val checkTicketResponseMapper: CheckTicketResponseMapper,
  private val checkSettingsResponseMapper: CheckSettingsResponseMapper
) : ResultNetworkDataSource {

  override fun getSettingsForCheck(): CheckLotterySettingsEntity {
    return checkSettingsResponseMapper.map(mmLotteApi.getCheckSettings().execute()!!.body()!!)
  }

  override fun checkNumber(ticketNumber: String): ResultEntity {
    return checkTicketResponseMapper.map(mmLotteApi.checkResult(ticketNumber).execute()!!.body()!!)
  }

  override fun getResult(): ResultListingEntity {
    return latestResultResponseMapper.map(mmLotteApi.getLatestResult().execute()!!.body()!!)
  }

}