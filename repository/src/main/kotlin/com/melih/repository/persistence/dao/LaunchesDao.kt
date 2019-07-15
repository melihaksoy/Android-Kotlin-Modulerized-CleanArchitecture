package com.melih.repository.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.melih.repository.entities.LaunchEntity

/**
 * DAO for list of [launches][LaunchEntity]
 */
@Dao
internal abstract class LaunchesDao {

    // region Queries

    @Query("SELECT * FROM Launches LIMIT :count")
    abstract suspend fun getLaunches(count: Int): List<LaunchEntity>

    @Query("SELECT * FROM Launches WHERE id=:id LIMIT 1")
    abstract suspend fun getLaunchById(id: Long): LaunchEntity?

    @Query("DELETE FROM Launches")
    abstract suspend fun nukeLaunches()
    // endregion

    // region Insertion

    @Insert
    abstract suspend fun saveLaunches(launches: List<LaunchEntity>)

    @Insert
    abstract suspend fun saveLaunch(launch: LaunchEntity)
    // endregion

    // region Transactions

    @Transaction
    open suspend fun updateLaunches(launches: List<LaunchEntity>) {
        nukeLaunches()
        saveLaunches(launches)
    }
    // endregion
}
