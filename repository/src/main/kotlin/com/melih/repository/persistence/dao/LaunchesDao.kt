package com.melih.repository.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.melih.repository.entities.LaunchEntity

/**
 * DAO for list of [launches][LaunchEntity]
 */
@Dao
internal abstract class LaunchesDao {

    // region Queries

    @Query("SELECT * FROM Launches ORDER BY launchStartTime DESC LIMIT :count OFFSET :page*:count")
    abstract suspend fun getLaunches(count: Int, page: Int): List<LaunchEntity>

    @Query("SELECT * FROM Launches WHERE id=:id LIMIT 1")
    abstract suspend fun getLaunchById(id: Long): LaunchEntity?

    @Query("DELETE FROM Launches")
    abstract suspend fun nukeLaunches()
    // endregion

    // region Insertion

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun saveLaunches(launches: List<LaunchEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun saveLaunch(launch: LaunchEntity)
    // endregion
}
