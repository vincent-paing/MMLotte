package com.mmlotte.lottery.domain.exception

/**
 * Created by Vincent on 12/12/18
 */
abstract class IllegalLotteryNumberInputException : Throwable()

class LotteryNumberInputLengthException : IllegalLotteryNumberInputException()