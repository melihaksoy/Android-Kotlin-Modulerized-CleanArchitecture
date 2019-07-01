package com.melih.repository.sources

import com.melih.repository.entities.LaunchEntity
import com.melih.repository.interactors.base.Reason
import com.melih.repository.interactors.base.Result
import io.mockk.called
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.coVerifyOrder
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.amshove.kluent.shouldBeInstanceOf
import org.amshove.kluent.shouldEqualTo
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class SourceManagerTest {

    private val networkSource = mockk<NetworkSource>(relaxed = true)
    private val persistenceSource = mockk<PersistenceSource>(relaxed = true)

    private val sourceManager = spyk(SourceManager(networkSource, persistenceSource))

    @Nested
    inner class GetNextLaunches {

        @Test
        @ExperimentalCoroutinesApi
        fun `should try to fetch, save and return result from persistance`() {
            runBlockingTest {
                val amount = 10
                coEvery { networkSource.getNextLaunches(any()) } returns Result.Success(listOf(LaunchEntity(id = 1012)))
                coEvery { persistenceSource.getNextLaunches(any()) } returns Result.Success(listOf(LaunchEntity(id = 1013)))

                val result = sourceManager.getNextLaunches(amount)

                coVerifyOrder {
                    networkSource.getNextLaunches(any())
                    persistenceSource.saveLaunches(any())
                    persistenceSource.getNextLaunches(any())
                }

                coVerify(exactly = 1) { networkSource.getNextLaunches(any()) }
                coVerify(exactly = 1) { persistenceSource.saveLaunches(any()) }
                coVerify(exactly = 1) { persistenceSource.getNextLaunches(any()) }

                result shouldBeInstanceOf Result.Success::class
                result.handleSuccess {
                    it.size shouldEqualTo 1
                    it[0].id shouldEqualTo 1013
                }
            }
        }

        @Test
        @ExperimentalCoroutinesApi
        fun `should not save response if fetching was failure and return result from persistance`() {
            runBlockingTest {
                val amount = 10
                coEvery { networkSource.getNextLaunches(any()) } returns Result.Failure(Reason.NetworkError())
                coEvery { persistenceSource.getNextLaunches(any()) } returns Result.Success(listOf(LaunchEntity(id = 1013)))

                val result = sourceManager.getNextLaunches(amount)

                coVerify { persistenceSource.saveLaunches(any()) wasNot called }
                coVerify(exactly = 1) { persistenceSource.getNextLaunches(any()) }

                result shouldBeInstanceOf Result.Success::class
                result.handleSuccess {
                    it.size shouldEqualTo 1
                    it[0].id shouldEqualTo 1013
                }
            }
        }

        @Test
        @ExperimentalCoroutinesApi
        fun `should return failure if network and persistance fails`() {
            runBlockingTest {
                val amount = 10
                coEvery { networkSource.getNextLaunches(any()) } returns Result.Failure(Reason.NetworkError())
                coEvery { persistenceSource.getNextLaunches(any()) } returns Result.Failure(Reason.PersistenceEmpty())

                val result = sourceManager.getNextLaunches(amount)

                coVerify { persistenceSource.saveLaunches(any()) wasNot called }
                coVerify(exactly = 1) { persistenceSource.getNextLaunches(any()) }

                result shouldBeInstanceOf Result.Failure::class
                result.handleFailure {
                    it shouldBeInstanceOf Reason.NoNetworkPersistenceEmpty::class
                }
            }
        }
    }

    @Nested
    inner class GetLaunchDetails {

        @Test
        fun `should return result from persistance immediately if it's found`() {
            runBlocking {
                coEvery { persistenceSource.getLaunchById(any()) } returns Result.Success(LaunchEntity(id = 1013))

                val result = sourceManager.getLaunchById(1)

                coVerify { networkSource.getLaunchById(any()) wasNot called }

                result shouldBeInstanceOf Result.Success::class
                result.handleSuccess {
                    it.id shouldEqualTo 1013
                }
            }
        }

        @Test
        fun `should fetch result from network if it's not found in persistance`() {
            runBlocking {
                coEvery {
                    persistenceSource.getLaunchById(any())
                } returns Result.Failure(Reason.PersistenceEmpty()) andThen Result.Success(LaunchEntity(id = 1013))

                coEvery { networkSource.getLaunchById(any()) } returns Result.Success(LaunchEntity(id = 1013))

                val result = sourceManager.getLaunchById(1)

                coVerify(exactly = 1) { networkSource.getLaunchById(any()) }
                coVerify(exactly = 1) { persistenceSource.saveLaunch(any()) }
                coVerify(exactly = 2) { persistenceSource.getLaunchById(any()) }

                result shouldBeInstanceOf Result.Success::class
                result.handleSuccess {
                    it.id shouldEqualTo 1013
                }
            }
        }
    }
}
