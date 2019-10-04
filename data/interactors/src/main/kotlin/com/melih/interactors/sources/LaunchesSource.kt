package com.melih.interactors.sources

import android.content.Context
import android.net.NetworkInfo
import com.melih.abstractions.data.ViewEntity
import com.melih.abstractions.deliverable.Failure
import com.melih.abstractions.deliverable.Result
import com.melih.abstractions.deliverable.Success
import com.melih.abstractions.mapper.Mapper
import com.melih.definitions.Source
import com.melih.repository.entities.LaunchEntity
import com.melih.repository.interactors.DEFAULT_LAUNCHES_AMOUNT
import com.melih.repository.interactors.base.ConnectionError
import com.melih.repository.interactors.base.EmptyResultError
import com.melih.repository.interactors.base.NetworkError
import com.melih.repository.interactors.base.PersistenceEmptyError
import com.melih.repository.interactors.base.ResponseError
import com.melih.repository.interactors.base.TimeoutError
import com.melih.repository.network.ApiImpl
import com.melih.repository.persistence.LaunchesDatabase
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject
import javax.inject.Provider

private const val DEFAULT_IMAGE_SIZE = 480

internal class LaunchesSource @Inject constructor(
    ctx: Context,
    private val apiImpl: ApiImpl,
    private val networkInfoProvider: Provider<NetworkInfo>
) : Source {

    //region Properties

    private val launchesDatabase = LaunchesDatabase.getInstance(ctx)

    private val isNetworkConnected: Boolean
        get() {
            val networkInfo = networkInfoProvider.get()
            return networkInfo != null && networkInfo.isConnected
        }
    //endregion

    //region Functions

    override suspend fun <T : ViewEntity> getNextLaunches(
        count: Int,
        page: Int, mapper: Mapper<LaunchEntity, T>
    ): Result<List<T>> {
        val networkResponse = safeExecute({
            apiImpl.getNextLaunches(count, page * DEFAULT_LAUNCHES_AMOUNT)
        }) { entity ->
            entity.launches
                .map(::transformRocketImageUrl)
                .saveLaunches()
                .map(mapper::convert)
        }

        return if (networkResponse is NetworkError) {
            launchesDatabase
                .launchesDao
                .getLaunches(count, page)
                .takeUnless { it.isNullOrEmpty() }
                ?.run {
                    Success(map(mapper::convert))
                } ?: Failure(PersistenceEmptyError())
        } else {
            networkResponse
        }
    }

    override suspend fun <T : ViewEntity> getLaunchById(
        id: Long,
        mapper: Mapper<LaunchEntity, T>
    ): Result<T> {
        return launchesDatabase
            .launchesDao
            .getLaunchById(id)
            .takeIf { it != null }
            ?.run {
                Success(mapper.convert(this))
            } ?: loadLaunchFromNetwork(id, mapper)
    }

    private suspend fun <T : ViewEntity> loadLaunchFromNetwork(
        id: Long,
        mapper: Mapper<LaunchEntity, T>
    ): Result<T> =
        safeExecute({
            apiImpl.getLaunchById(id)
        }) {
            mapper.convert(
                transformRocketImageUrl(it)
                    .saveLaunch()
            )
        }

    private suspend fun List<LaunchEntity>.saveLaunches() = run {
        launchesDatabase.launchesDao.saveLaunches(this)
        this
    }

    private suspend fun LaunchEntity.saveLaunch() = run {
        launchesDatabase.launchesDao.saveLaunch(this)
        this
    }

    private inline fun <T, R> safeExecute(
        block: () -> Response<T>,
        transform: (T) -> R
    ) =
        if (isNetworkConnected) {
            try {
                block().extractResponseBody(transform)
            } catch (e: IOException) {
                Failure(TimeoutError())
            }
        } else {
            Failure(ConnectionError())
        }

    private inline fun <T, R> Response<T>.extractResponseBody(transform: (T) -> R) =
        if (isSuccessful) {
            body()?.let {
                Success(transform(it))
            } ?: Failure(EmptyResultError())
        } else {
            Failure(ResponseError())
        }

    private fun transformRocketImageUrl(launch: LaunchEntity) =
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

    private fun transformImageUrl(imageUrl: String, supportedSizes: IntArray) =
        try {
            val urlSplit = imageUrl.split("_")
            val url = urlSplit[0]
            val format = urlSplit[1].split(".")[1]

            val requestedSize = if (!supportedSizes.contains(DEFAULT_IMAGE_SIZE)) {
                supportedSizes.last { it < DEFAULT_IMAGE_SIZE }
            } else {
                DEFAULT_IMAGE_SIZE
            }

            "${url}_$requestedSize.$format"
        } catch (e: Exception) {
            imageUrl
        }
    //endregion
}
