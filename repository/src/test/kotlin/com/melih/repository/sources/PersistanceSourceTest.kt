package com.melih.repository.sources

import com.melih.repository.entities.LaunchEntity
import com.melih.repository.interactors.base.Reason
import com.melih.repository.interactors.base.Result
import com.melih.repository.persistence.LaunchesDatabase
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldBeInstanceOf
import org.amshove.kluent.shouldEqualTo
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class PersistanceSourceTest {

    private val dbImplementation = mockk<LaunchesDatabase>(relaxed = true)
    private val source = spyk(PersistenceSource(dbImplementation))

    @Nested
    inner class GetNextLaunches {

        @Test
        @ExperimentalCoroutinesApi
        fun `should return persistance empty error when db is empty`() {
            runBlockingTest {
                coEvery { dbImplementation.launchesDao.getLaunches(any()) } returns emptyList()

                val result = source.getNextLaunches(10)
                result shouldBeInstanceOf Result.Failure::class
                result.handleFailure {
                    it shouldBeInstanceOf Reason.PersistenceEmpty::class
                }
            }
        }

        @Test
        @ExperimentalCoroutinesApi
        fun `should return success with data if db is not empty`() {
            runBlockingTest {
                coEvery { dbImplementation.launchesDao.getLaunches(any()) } returns listOf(LaunchEntity(id = 1013))

                val result = source.getNextLaunches(10)
                result shouldBeInstanceOf Result.Success::class
                result.handleSuccess {
                    it.isEmpty() shouldBe false
                    it.size shouldEqualTo 1
                    it[0].id shouldEqualTo 1013
                }
            }
        }
    }
}
