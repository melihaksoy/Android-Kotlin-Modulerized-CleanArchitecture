package com.melih.repository.sources

import android.net.NetworkInfo
import com.melih.repository.DEFAULT_IMAGE_SIZE
import com.melih.repository.Repository
import com.melih.repository.entities.LaunchEntity
import com.melih.repository.interactors.DEFAULT_LAUNCH_COUNT
import com.melih.repository.interactors.base.EmptyResultError
import com.melih.repository.interactors.base.Failure
import com.melih.repository.interactors.base.NetworkError
import com.melih.repository.interactors.base.Reason
import com.melih.repository.interactors.base.ResponseError
import com.melih.repository.interactors.base.Result
import com.melih.repository.interactors.base.Success
import com.melih.repository.interactors.base.TimeoutError
import com.melih.repository.network.ApiImpl
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject
import javax.inject.Provider

/**
 * NetworkSource for fetching results using api and wrapping them as contracted in [repository][Repository],
 * returning either [failure][Failure] with proper [reason][Reason] or [success][Success] with data
 */
internal class NetworkSource @Inject constructor(
    private val apiImpl: ApiImpl,
    private val networkInfoProvider: Provider<NetworkInfo>
) : Repository() {
    // region Properties

    private val isNetworkConnected: Boolean
        get() {
            val networkInfo = networkInfoProvider.get()
            return networkInfo != null && networkInfo.isConnected
        }
    // endregion

    // region Functions

    override suspend fun getNextLaunches(count: Int, page: Int): Result<List<LaunchEntity>> =
        safeExecute({
            apiImpl.getNextLaunches(count, page * DEFAULT_LAUNCH_COUNT)
        }) { entity ->
            entity.launches.map { launch ->
                if (!launch.rocket.imageURL.isNotBlank()) {
                    launch.copy(
                        rocket = launch.rocket.copy(
                            imageURL = transformImageUrl(
                                launch.rocket.imageURL,
                                launch.rocket.imageSizes
                            )
                        )
                    )
                } else {
                    launch
                }
            }
        }

    override suspend fun getLaunchById(id: Long): Result<LaunchEntity> =
        safeExecute({
            apiImpl.getLaunchById(id)
        }) {
            if (!it.rocket.imageURL.isNotBlank()) {
                it.copy(
                    rocket = it.rocket.copy(
                        imageURL = transformImageUrl(it.rocket.imageURL, it.rocket.imageSizes)
                    )
                )
            } else {
                it
            }
        }

    private suspend inline fun <T, R> safeExecute(
        block: suspend () -> Response<T>,
        transform: (T) -> R
    ) =
        if (isNetworkConnected) {
            try {
                block().extractResponseBody(transform)
            } catch (e: IOException) {
                Failure(TimeoutError())
            }
        } else {
            Failure(NetworkError())
        }

    private inline fun <T, R> Response<T>.extractResponseBody(transform: (T) -> R) =
        if (isSuccessful) {
            body()?.let {
                Success(transform(it))
            } ?: Failure(EmptyResultError())
        } else {
            Failure(ResponseError())
        }

    private fun transformImageUrl(imageUrl: String, supportedSizes: IntArray) =
        try {
            val urlSplit = imageUrl.split("_")
            val url = urlSplit[0]
            val format = urlSplit[1].split(".")[1]

            var requestedSize = DEFAULT_IMAGE_SIZE

            if (!supportedSizes.contains(requestedSize)) {
                requestedSize = supportedSizes.last { it < requestedSize }
            }

            "${url}_$requestedSize.$format"
        } catch (e: Exception) {
            imageUrl
        }
    // endregion
}
