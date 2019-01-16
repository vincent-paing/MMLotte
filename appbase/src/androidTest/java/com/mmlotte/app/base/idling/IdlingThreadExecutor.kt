package com.mmlotte.app.base.idling

import androidx.test.espresso.idling.concurrent.IdlingThreadPoolExecutor
import com.mmlotte.lottery.domain.executor.ThreadExecutor
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by Vincent on 12/6/18
 */
@Singleton
class IdlingThreadExecutor : ThreadExecutor, IdlingThreadPoolExecutor(
  "IdliingThread",
  INITIAL_POOL_SIZE,
  MAX_POOL_SIZE,
  KEEP_ALIVE_TIME,
  KEEP_ALIVE_TIME_UNIT,
  LinkedBlockingQueue<Runnable>(),
  JobThreadFactory()
) {

  private class JobThreadFactory : ThreadFactory {
    private var counter = 0

    override fun newThread(runnable: Runnable): Thread {
      return Thread(runnable, THREAD_NAME + counter++)
    }

    companion object {
      private val THREAD_NAME = "android_"
    }
  }

  companion object {

    private const val INITIAL_POOL_SIZE = 3
    private const val MAX_POOL_SIZE = 5

    // Sets the amount of time an idle thread waits before terminating
    private const val KEEP_ALIVE_TIME = 10L

    // Sets the Time Unit to seconds
    private val KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS
  }

}