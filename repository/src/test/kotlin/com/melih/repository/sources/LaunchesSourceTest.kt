package com.melih.repository.sources

import android.content.Context
import android.net.NetworkInfo
import com.melih.abstractions.data.ViewEntity
import com.melih.abstractions.deliverable.Failure
import com.melih.abstractions.deliverable.Success
import com.melih.abstractions.deliverable.onFailure
import com.melih.abstractions.deliverable.onSuccess
import com.melih.abstractions.mapper.Mapper
import com.melih.repository.R
import com.melih.repository.entities.LaunchEntity
import com.melih.repository.entities.LaunchesEntity
import com.melih.repository.interactors.base.EmptyResultError
import com.melih.repository.interactors.base.NetworkError
import com.melih.repository.interactors.base.ResponseError
import com.melih.repository.network.ApiImpl
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.amshove.kluent.shouldBeInstanceOf
import org.amshove.kluent.shouldEqualTo
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import javax.inject.Provider

@UseExperimental(ExperimentalCoroutinesApi::class)
class LaunchesSourceTest {

    //region Properties

    private val testId = 1013L
    //region Mocks

    private val apiImpl = mockk<ApiImpl>(relaxed = true)
    private val ctx = mockk<Context>(relaxed = true)

    private val networkInfoProvider = mockk<Provider<NetworkInfo>>(relaxed = true) {
        every { get() } returns mockk(relaxed = true)
    }
    //endregion

    //region Spies

    private val source = spyk(LaunchesSource(ctx, apiImpl, networkInfoProvider))
    private val mapper = spyk(TestMapper())
    //endregion
    //endregion

    @Nested
    inner class GetNextLaunches {

        @Test
        fun `should return network error when internet is not connected`() {
            every { networkInfoProvider.get().isConnected } returns false

            runBlockingTest {
                val result = source.getNextLaunches(1, 0, mapper)

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
                val result = source.getNextLaunches(1, 0, mapper)

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
                val result = source.getNextLaunches(1, 0, mapper)

                result shouldBeInstanceOf Failure::class
                result.onFailure {
                    it shouldBeInstanceOf EmptyResultError::class
                }
            }
        }

        //TODO success test

        //@Test
        //fun `should return success with data if execution is successful`() {
        //    every { networkInfoProvider.get().isConnected } returns true
        //    coEvery { apiImpl.getNextLaunches(any(), any()).isSuccessful } returns true
        //    coEvery { apiImpl.getNextLaunches(any(), any()).body() } returns LaunchesEntity(launches = listOf(LaunchEntity(id = testId)))
        //
        //    runBlockingTest {
        //        val result = source.getNextLaunches(1, 0, mapper)
        //
        //        verify(exactly = 1) { mapper.convert(any()) }
        //
        //        result shouldBeInstanceOf Success::class
        //        result.onSuccess {
        //            it shouldBeInstanceOf List::class
        //            it.size shouldEqualTo 1
        //
        //            with(it[0]) {
        //                this shouldBeInstanceOf TestViewEntity::class
        //                this.id shouldEqualTo testId
        //            }
        //        }
        //    }
        //}
    }

    inner class TestMapper : Mapper<LaunchEntity, TestViewEntity>() {
        override fun convert(t: LaunchEntity): TestViewEntity = TestViewEntity(t.id)

    }

    data class TestViewEntity(val id: Long) : ViewEntity
}
