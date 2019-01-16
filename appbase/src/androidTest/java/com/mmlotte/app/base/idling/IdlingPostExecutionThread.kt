package com.mmlotte.app.base.idling

import androidx.test.espresso.IdlingRegistry
import com.mmlotte.lottery.domain.executor.PostExecutionThread
import com.squareup.rx2.idler.Rx2Idler
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Singleton

/**
 * Created by Vincent on 12/6/18
 */
@Singleton
class IdlingPostExecutionThread : PostExecutionThread {
  override val scheduler: Scheduler
    get() = {
      val idler = Rx2Idler.wrap(AndroidSchedulers.mainThread(), "RxIdler2 Wrapped Scheduler")
      IdlingRegistry.getInstance().register(idler)
      idler
    }.invoke()

}
