package com.melih.repository.interactors.base

import androidx.annotation.StringRes
import com.melih.abstractions.deliverable.Reason
import com.melih.repository.R


/**
 * Class to encapsulate failure reasons in repository
 */
sealed class RepositoryErrorReason(@StringRes override val messageRes: Int) : Reason()

//region Subclasses

class NetworkError : RepositoryErrorReason(R.string.reason_network)
class EmptyResultError : RepositoryErrorReason(R.string.reason_empty_body)
class GenericError : RepositoryErrorReason(R.string.reason_generic)
class ResponseError : RepositoryErrorReason(R.string.reason_response)
class TimeoutError : RepositoryErrorReason(R.string.reason_timeout)
class PersistenceEmpty : RepositoryErrorReason(R.string.reason_persistance_empty)
class NoNetworkPersistenceEmpty : RepositoryErrorReason(R.string.reason_no_network_persistance_empty)
//endregion
