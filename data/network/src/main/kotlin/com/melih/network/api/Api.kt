package com.melih.repository.network

import com.melih.repository.entities.LaunchEntity
import com.melih.repository.entities.LaunchesEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Retrofit interface for networking
 */
internal interface Api {

    //region Get

    @GET("launch/next/{count}")
    suspend fun getNextLaunches(
        @Path("count") count: Int,
        @Query("offset") offset: Int
    ): Response<LaunchesEntity>

    @GET("launch/{id}")
    suspend fun getLaunchById(
        @Path("id") id: Long
    ): Response<LaunchEntity>
    //endregion
}
