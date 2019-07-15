package com.melih.repository.network

import com.melih.repository.entities.LaunchEntity
import com.melih.repository.entities.LaunchesEntity
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

internal class ApiImpl @Inject constructor() : Api {

    // region Properties

    private val service by lazy {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        Retrofit.Builder()
            .client(
                OkHttpClient.Builder()
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
    // endregion

    override suspend fun getNextLaunches(count: Int): Response<LaunchesEntity> =
        service.getNextLaunches(count)

    override suspend fun getLaunchById(id: Long): Response<LaunchEntity> =
        service.getLaunchById(id)
}
