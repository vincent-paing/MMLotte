package com.mmlotte.lottery.domain.executor

import io.reactivex.Scheduler

/**
 * Created by Vincent on 9/20/18
 */
interface PostExecutionThread {
  val scheduler: Scheduler
}