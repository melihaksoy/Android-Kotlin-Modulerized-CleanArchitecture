@file:UseExperimental(ExperimentalCoroutinesApi::class)

package com.melih.repository.sources

import android.net.NetworkInfo
import com.melih.repository.R
import com.melih.repository.entities.LaunchEntity
import com.melih.repository.entities.LaunchesEntity
import com.melih.repository.interactors.base.EmptyResultError
import com.melih.repository.interactors.base.Failure
import com.melih.repository.interactors.base.NetworkError
import com.melih.repository.interactors.base.ResponseError
import com.melih.repository.interactors.base.Success
import com.melih.repository.interactors.base.onFailure
import com.melih.repository.interactors.base.onSuccess
import com.melih.repository.network.ApiImpl
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.amshove.kluent.shouldBeInstanceOf
import org.amshove.kluent.shouldEqualTo
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import javax.inject.Provider

class NetworkSourceTest {

    private val apiImpl = mockk<ApiImpl>(relaxed = true)
    private val networkInfoProvider = mockk<Provider<NetworkInfo>>(relaxed = true) {
        every { get() } returns mockk(relaxed = true)
    }

    private val source = spyk(NetworkSource(apiImpl, networkInfoProvider))

    @Nested
    inner class GetNextLaunches {

        @Test
        fun `should return network error when internet is not connected`() {
            every { networkInfoProvider.get().isConnected } returns false

            runBlockingTest {
                val result = source.getNextLaunches(1, 0)

                result shouldBeInstanceOf Failure::class
                result.onFailure {
                    it shouldBeInstanceOf NetworkError::class
                }
            }
        }

        @Test
        fun `should return response error when it is not successful`() {
            every { networkInfoProvider.get().isConnected } returns true
            coEvery { apiImpl.getNextLaunches(any(), any()).isSuccessful } returns false

            runBlockingTest {
                val result = source.getNextLaunches(1, 0)

                result shouldBeInstanceOf Failure::class
                result.onFailure {
                    it shouldBeInstanceOf ResponseError::class
                    (it as ResponseError).messageRes shouldEqualTo R.string.reason_response
                }
            }
        }

        @Test
        fun `should return empty result error when body is null`() {
            every { networkInfoProvider.get().isConnected } returns true
            coEvery { apiImpl.getNextLaunches(any(), any()).isSuccessful } returns true
            coEvery { apiImpl.getNextLaunches(any(), any()).body() } returns null

            runBlockingTest {
                val result = source.getNextLaunches(1, 0)

                result shouldBeInstanceOf Failure::class
                result.onFailure {
                    it shouldBeInstanceOf EmptyResultError::class
                }
            }
        }

        @Test
        fun `should return success with data if execution is successful`() {
            every { networkInfoProvider.get().isConnected } returns true
            coEvery { apiImpl.getNextLaunches(any(), any()).isSuccessful } returns true
            coEvery { apiImpl.getNextLaunches(any(), any()).body() } returns LaunchesEntity(launches = listOf(LaunchEntity(id = 1013)))

            runBlockingTest {
                val result = source.getNextLaunches(1, 0)

                result shouldBeInstanceOf Success::class
                result.onSuccess {
                    it shouldBeInstanceOf List::class
                    it.size shouldEqualTo 1
                    it[0].id shouldEqualTo 1013
                }
            }
        }
    }
}
