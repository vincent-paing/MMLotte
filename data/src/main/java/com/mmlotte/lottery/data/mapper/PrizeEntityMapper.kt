package com.mmlotte.lottery.data.mapper

import com.mmlotte.lottery.data.entity.PrizeEntity
import com.mmlotte.lottery.domain.MapFunction
import com.mmlotte.lottery.domain.model.Prize
import javax.inject.Inject

/**
 * Created by Vincent on 12/6/18
 */
class PrizeEntityMapper @Inject constructor() : MapFunction<PrizeEntity, Prize> {

  override fun map(item: PrizeEntity): Prize {
    return Prize(
      prizeId = item.prizeId,
      prizeTitle = item.prizeTitle,
      winningNumbers = item.winningNumbers
    )
  }

}