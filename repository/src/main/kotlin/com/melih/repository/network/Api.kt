package com.melih.repository.network

import com.melih.repository.entities.LaunchEntity
import com.melih.repository.entities.LaunchesEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Retrofit interface for networking
 */
interface Api {

    @GET("launch/next/{count}")
    suspend fun getNextLaunches(@Path("count") count: Int): Response<LaunchesEntity>

    @GET("launch/{id}")
    suspend fun getLaunchById(@Path("id") id: Long): Response<LaunchEntity>
}