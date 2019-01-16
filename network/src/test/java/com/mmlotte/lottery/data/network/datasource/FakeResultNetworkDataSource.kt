package com.mmlotte.lottery.data.network.datasource

import com.mmlotte.lottery.data.datasource.network.ResultNetworkDataSource
import com.mmlotte.lottery.data.entity.CheckLotterySettingsEntity
import com.mmlotte.lottery.data.entity.PrizeEntity
import com.mmlotte.lottery.data.entity.ResultEntity
import com.mmlotte.lottery.data.entity.ResultListingEntity
import org.threeten.bp.Instant

/**
 * Created by Vincent on 9/20/18
 */
class FakeResultNetworkDataSource : ResultNetworkDataSource {

  override fun getSettingsForCheck(): CheckLotterySettingsEntity {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun checkNumber(ticketNumber: String): ResultEntity {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  var getResultListingMock: (() -> ResultListingEntity) = {
    ResultListingEntity(
      resultDate = Instant.now(),
      prizeEntities = listOf(
        PrizeEntity(
          prizeId = 1,
          prizeTitle = "သိန်း (၅၀၀) ကျပ်",
          winningNumbers = listOf("1234", "2345")
        )
      )
    )
  }

  override fun getResult(): ResultListingEntity {
    return getResultListingMock.invoke()
  }

}