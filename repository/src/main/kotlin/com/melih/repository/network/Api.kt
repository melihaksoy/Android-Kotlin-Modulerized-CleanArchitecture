package com.melih.repository.network

import com.melih.repository.entities.LaunchEntity
import com.melih.repository.entities.LaunchesEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Retrofit interface for networking
 */
internal interface Api {

    @GET("launch")
    suspend fun getNextLaunches(@Query("count") count: Int, @Query("offset") offset: Int): Response<LaunchesEntity>

    @GET("launch")
    suspend fun getLaunchById(@Query("id") id: Long): Response<LaunchEntity>
}
