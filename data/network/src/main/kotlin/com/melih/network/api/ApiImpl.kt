package com.melih.network

import com.melih.definitions.entities.LaunchEntity
import com.melih.definitions.entities.LaunchesEntity
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

internal const val TIMEOUT_DURATION = 7L

class ApiImpl @Inject constructor() : Api {

    //region Properties

    private val service by lazy {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        Retrofit.Builder()
            .client(
                OkHttpClient.Builder()
                    .connectTimeout(TIMEOUT_DURATION, TimeUnit.SECONDS)
                    .readTimeout(TIMEOUT_DURATION, TimeUnit.SECONDS)
                    .addInterceptor(
                        HttpLoggingInterceptor().apply {
                            level = HttpLoggingInterceptor.Level.BODY
                        }
                    ).build()
            )
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl("https://launchlibrary.net/1.4/")
            .build()
            .create(Api::class.java)
    }
    //endregion

    //region Functions

    override suspend fun getNextLaunches(
        count: Int,
        offset: Int
    ): Response<LaunchesEntity> =
        service.getNextLaunches(count, offset)

    override suspend fun getLaunchById(
        id: Long
    ): Response<LaunchEntity> =
        service.getLaunchById(id)
    //endregion
}
