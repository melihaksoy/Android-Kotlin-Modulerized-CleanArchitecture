package com.melih.repository.interactors.base

import androidx.annotation.StringRes
import com.melih.repository.R


/**
 * [Failure] reasons
 */
sealed class Reason(@StringRes val messageRes: Int)

class NetworkError : Reason(R.string.reason_network)
class EmptyResultError : Reason(R.string.reason_empty_body)
class GenericError : Reason(R.string.reason_generic)
class ResponseError : Reason(R.string.reason_response)
class TimeoutError : Reason(R.string.reason_timeout)
class PersistenceEmpty : Reason(R.string.reason_persistance_empty)
class NoNetworkPersistenceEmpty : Reason(R.string.reason_no_network_persistance_empty)
