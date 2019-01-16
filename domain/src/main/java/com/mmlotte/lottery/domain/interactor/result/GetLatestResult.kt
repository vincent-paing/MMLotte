package com.mmlotte.lottery.domain.interactor.result

import com.mmlotte.lottery.domain.executor.PostExecutionThread
import com.mmlotte.lottery.domain.executor.ThreadExecutor
import com.mmlotte.lottery.domain.interactor.SingleUseCase
import com.mmlotte.lottery.domain.model.LotteryResult
import com.mmlotte.lottery.domain.repository.ResultRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Vincent on 12/7/18
 */
class GetLatestResult @Inject constructor(
  postExecutionThread: PostExecutionThread,
  threadExecutor: ThreadExecutor,
  private val resultRepository: ResultRepository
) : SingleUseCase<LotteryResult, Unit>(postExecutionThread, threadExecutor) {

  override fun provideSingle(params: Unit): Single<LotteryResult> {
    return resultRepository.getResult()
  }

}

