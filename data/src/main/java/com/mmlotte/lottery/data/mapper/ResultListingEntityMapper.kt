package com.mmlotte.lottery.data.mapper

import com.mmlotte.lottery.data.entity.ResultListingEntity
import com.mmlotte.lottery.domain.MapFunction
import com.mmlotte.lottery.domain.model.LotteryResult
import org.threeten.bp.ZoneId
import javax.inject.Inject

/**
 * Created by Vincent on 12/6/18
 */
class ResultListingEntityMapper @Inject constructor(private val prizeEntityMapper: PrizeEntityMapper) :
  MapFunction<ResultListingEntity, LotteryResult> {

  override fun map(item: ResultListingEntity): LotteryResult {
    return LotteryResult(
      resultDate = item.resultDate.atZone(ZoneId.of("Asia/Rangoon")),
      prizeList = item.prizeEntities.map(prizeEntityMapper::map)
    )
  }
}

