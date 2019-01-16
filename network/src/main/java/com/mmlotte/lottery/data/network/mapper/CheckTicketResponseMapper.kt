package com.mmlotte.lottery.data.network.mapper

import com.mmlotte.lottery.data.entity.ResultEntity
import com.mmlotte.lottery.data.network.entity.CheckTicketResponse
import com.mmlotte.lottery.domain.MapFunction
import javax.inject.Inject

/**
 * Created by Vincent on 12/11/18
 */
class CheckTicketResponseMapper @Inject constructor(
  private val prizeResponseMapper: PrizeResponseMapper
) : MapFunction<CheckTicketResponse, ResultEntity> {

  override fun map(item: CheckTicketResponse): ResultEntity {
    val item = ResultEntity(
      prize = if (item.isAWinningTicket && item.prize != null) {
        prizeResponseMapper.map(item.prize)
      } else {
        null
      }
    )

    return item
  }

}