//package com.melih.repository.sources
//
//import android.net.NetworkInfo
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.map
//import com.melih.repository.DEFAULT_IMAGE_SIZE
//import com.melih.repository.Repository
//import com.melih.repository.entities.LaunchEntity
//import com.melih.repository.interactors.base.Reason
//import com.melih.repository.interactors.base.Result
//import com.melih.repository.network.ApiImpl
//import com.melih.repository.persistence.LaunchesDatabase
//import kotlinx.coroutines.async
//import kotlinx.coroutines.coroutineScope
//import retrofit2.Response
//import java.io.IOException
//import javax.inject.Inject
//import javax.inject.Provider
//
//class SingleTruthSource @Inject constructor(
//    private val api: ApiImpl,
//    private val launchesDatabase: LaunchesDatabase,
//    private val networkInfoProvider: Provider<NetworkInfo>
//) : Repository() {
//
//    private val isNetworkConnected: Boolean
//        get() {
//            val networkInfo = networkInfoProvider.get()
//            return networkInfo != null && networkInfo.isConnected
//        }
//
//    // region Functions
//
//    override suspend fun getNextLaunches(count: Int): LiveData<Result<List<LaunchEntity>>> {
//        coroutineScope {
//            async {
//                val result = safeExecute(api::getNextLaunches, count) { entity ->
//                    entity.launches.map { launch ->
//                        if (!launch.rocket.imageURL.isNotBlank()) {
//                            launch.copy(
//                                rocket = launch.rocket.copy(
//                                    imageURL = transformImageUrl(
//                                        launch.rocket.imageURL,
//                                        launch.rocket.imageSizes
//                                    )
//                                )
//                            )
//                        } else {
//                            launch
//                        }
//                    }
//                }
//
//                saveLaunches(result)
//            }
//        }
//
//        return launchesDatabase
//            .launchesDao
//            .getLaunches(count)
//            .map {
//                Result.Success(it)
//            }
//    }
//
//    override suspend fun getLaunchById(id: Long): LiveData<Result<LaunchEntity>> {
//
//    }
//
//    internal suspend fun saveLaunches(launches: List<LaunchEntity>) {
//        launchesDatabase.launchesDao.updateLaunches(launches)
//    }
//
//    internal suspend fun saveLaunch(launch: LaunchEntity) {
//        launchesDatabase.launchesDao.saveLaunch(launch)
//    }
//
//    private suspend inline fun <T, P, R> safeExecute(
//        block: suspend (param: P) -> Response<T>,
//        param: P,
//        transform: (T) -> R
//    ) =
//        if (isNetworkConnected) {
//            try {
//                block(param).extractResponseBody(transform)
//            } catch (e: IOException) {
//                Result.Failure(Reason.TimeoutError())
//            }
//        } else {
//            Result.Failure(Reason.NetworkError())
//        }
//
//    private inline fun <T, R> Response<T>.extractResponseBody(transform: (T) -> R) =
//        if (isSuccessful) {
//            body()?.let {
//                Result.Success(transform(it))
//            } ?: Result.Failure(Reason.EmptyResultError())
//        } else {
//            Result.Failure(Reason.ResponseError())
//        }
//
//    private fun transformImageUrl(imageUrl: String, supportedSizes: IntArray) =
//        try {
//            val urlSplit = imageUrl.split("_")
//            val url = urlSplit[0]
//            val format = urlSplit[1].split(".")[1]
//
//            var requestedSize = DEFAULT_IMAGE_SIZE
//
//            if (!supportedSizes.contains(requestedSize)) {
//                requestedSize = supportedSizes.last { it < requestedSize }
//            }
//
//            "${url}_$requestedSize.$format"
//        } catch (e: Exception) {
//            imageUrl
//        }
//}
