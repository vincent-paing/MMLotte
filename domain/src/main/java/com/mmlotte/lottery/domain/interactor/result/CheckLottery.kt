package com.mmlotte.lottery.domain.interactor.result

import com.mmlotte.lottery.domain.interactor.MaybeUseCase
import com.mmlotte.lottery.domain.exception.LotteryNumberInputLengthException
import com.mmlotte.lottery.domain.executor.PostExecutionThread
import com.mmlotte.lottery.domain.executor.ThreadExecutor
import com.mmlotte.lottery.domain.helper.MyanmarNumberConverter
import com.mmlotte.lottery.domain.helper.ValidationResult
import com.mmlotte.lottery.domain.helper.ValidationResult.Invalid
import com.mmlotte.lottery.domain.helper.ValidationResult.Valid
import com.mmlotte.lottery.domain.interactor.result.CheckLottery.Params
import com.mmlotte.lottery.domain.model.Prize
import com.mmlotte.lottery.domain.repository.ResultRepository
import io.reactivex.Maybe
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Vincent on 12/11/18
 */
class CheckLottery
@Inject
constructor(
  private val resultRepository: ResultRepository,
  postExecutionThread: PostExecutionThread,
  threadExecutor: ThreadExecutor
) :
  MaybeUseCase<Prize, Params>(postExecutionThread, threadExecutor) {

  data class Params(
    val character: String,
    val number: String
  )

  override fun provideMaybe(params: CheckLottery.Params): Maybe<Prize> {

    return Single.fromCallable {
      validateInputValue(params)
    }.map {

      when (it) {
        Valid -> {
          params
        }
        is Invalid -> {
          throw it.throwable
        }
      }
    }.map {
      normalizeInputValue(it)
    }.flatMapMaybe {
      resultRepository.checkForResult("${it.character}${it.number}")
    }
  }

  /**
   * A function that fixes input problem such as whitespaces, mixed burmese and english input and much more
   */
  internal fun normalizeInputValue(params: CheckLottery.Params): CheckLottery.Params {
    //Convert English to Burmese number
    var normalizedInput = MyanmarNumberConverter.getEngNumber(params.number)

    //Remove white spaces
    normalizedInput = normalizedInput.replace(" ", "")

    return params.copy(number = normalizedInput)
  }

  /**
   * Input validation function
   */
  internal fun validateInputValue(params: CheckLottery.Params): ValidationResult {

    if (params.number.length != 6) {
      return ValidationResult.Invalid(LotteryNumberInputLengthException())
    }


    return ValidationResult.Valid
  }
}

