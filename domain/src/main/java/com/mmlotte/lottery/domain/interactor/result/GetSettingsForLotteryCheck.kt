package com.mmlotte.lottery.domain.interactor.result

import com.mmlotte.lottery.domain.executor.PostExecutionThread
import com.mmlotte.lottery.domain.executor.ThreadExecutor
import com.mmlotte.lottery.domain.interactor.SingleUseCase
import com.mmlotte.lottery.domain.model.CheckSettings
import com.mmlotte.lottery.domain.repository.ResultRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Vincent on 12/12/18
 */
class GetSettingsForLotteryCheck @Inject constructor(
  private val resultRepository: ResultRepository,
  postExecutionThread: PostExecutionThread,
  threadExecutor: ThreadExecutor
) : SingleUseCase<CheckSettings, Unit>(postExecutionThread, threadExecutor) {

  override fun provideSingle(params: Unit): Single<CheckSettings> {
    return resultRepository.getSettingsForCheck()
  }
}

