package com.melih.repository.interactors.base

import androidx.annotation.StringRes
import com.melih.abstractions.deliverable.Reason
import com.melih.interactors.R

sealed class InteractionErrorReason(@StringRes override val messageRes: Int) : Reason()

class GenericError(@StringRes override val messageRes: Int = R.string.reason_generic) : InteractionErrorReason(messageRes)

sealed class NetworkError(override val messageRes: Int) : InteractionErrorReason(messageRes)
class ConnectionError : NetworkError(R.string.reason_network)
class EmptyResultError : NetworkError(R.string.reason_empty_body)
class ResponseError : NetworkError(R.string.reason_response)
class TimeoutError : NetworkError(R.string.reason_timeout)

class PersistenceEmptyError : InteractionErrorReason(R.string.reason_persistance_empty)
