package com.mmlotte.lottery.domain

/**
 * Created by Vincent on 12/6/18
 */
interface MapFunction<T, E> {

  fun map(item: T): E
}