package com.mmlotte.lottery.data.network.mapper

import com.mmlotte.lottery.data.entity.ResultListingEntity
import com.mmlotte.lottery.data.network.entity.LatestResultResponse
import com.mmlotte.lottery.domain.MapFunction
import org.threeten.bp.Instant
import javax.inject.Inject

/**
 * Created by Vincent on 12/11/18
 */
class LatestResultResponseMapper @Inject constructor(
  private val prizeResponseMapper: PrizeResponseMapper
) : MapFunction<LatestResultResponse, ResultListingEntity> {

  override fun map(item: LatestResultResponse): ResultListingEntity {
    return ResultListingEntity(
      resultDate = Instant.ofEpochSecond(item.date),
      prizeEntities = item.prizeList.map(prizeResponseMapper::map)
    )
  }

}