package com.mmlotte.lottery.data.network.mapper

import com.mmlotte.lottery.data.entity.PrizeEntity
import com.mmlotte.lottery.data.network.entity.PrizeResponse
import com.mmlotte.lottery.domain.MapFunction
import javax.inject.Inject

/**
 * Created by Vincent on 12/11/18
 */
class PrizeResponseMapper @Inject constructor() : MapFunction<PrizeResponse, PrizeEntity> {
  override fun map(item: PrizeResponse): PrizeEntity {
    return PrizeEntity(
      item.prizeId,
      item.nameMm,
      item.winningNumbers
    )
  }

}
