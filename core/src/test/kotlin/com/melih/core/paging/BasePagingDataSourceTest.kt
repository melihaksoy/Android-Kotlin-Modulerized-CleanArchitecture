@file:UseExperimental(ExperimentalCoroutinesApi::class)

package com.melih.core.paging

import androidx.paging.PageKeyedDataSource
import com.melih.abstractions.data.ViewEntity
import com.melih.abstractions.deliverable.Failure
import com.melih.abstractions.deliverable.Reason
import com.melih.abstractions.deliverable.Result
import com.melih.abstractions.deliverable.State
import com.melih.abstractions.deliverable.Success
import com.melih.core.BaseTestWithMainThread
import com.melih.core.base.paging.BasePagingDataSource
import com.melih.core.testObserve
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldBeInstanceOf
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class BasePagingDataSourceTest : BaseTestWithMainThread() {

    val source = spyk(TestSource())
    val failureSource = spyk(TestFailureSource())

    val data = 10
    val errorMessageResId = 1313

    @Nested
    inner class BasePagingSource {

        @Nested
        inner class LoadInitial {

            @Test
            fun `should update state accordingly`() {
                val params = mockk<PageKeyedDataSource.LoadInitialParams<Int>>(relaxed = true)
                val callback = mockk<PageKeyedDataSource.LoadInitialCallback<Int, TestViewEntity>>(relaxed = true)

                runBlocking {

                    // Fake loading
                    source.loadInitial(params, callback)

                    source.stateData.testObserve {
                        it shouldBeInstanceOf State.Loading::class
                    }
                }
            }

            @Test
            fun `should update error Error accordingly`() {
                val params = PageKeyedDataSource.LoadInitialParams<Int>(10, false)
                val callback = mockk<PageKeyedDataSource.LoadInitialCallback<Int, TestViewEntity>>(relaxed = true)

                runBlocking {

                    // Fake loading
                    failureSource.loadInitial(params, callback)

                    failureSource.reasonData.testObserve {
                        it shouldBeInstanceOf TestFailureReason::class
                    }
                }
            }
        }

        @Nested
        inner class LoadAfter {

            @Test
            fun `should update state accordingly`() {
                val params = PageKeyedDataSource.LoadParams(2, 10)
                val callback = mockk<PageKeyedDataSource.LoadCallback<Int, TestViewEntity>>(relaxed = true)

                runBlocking {

                    // Fake loading
                    source.loadAfter(params, callback)

                    source.stateData.testObserve {
                        it shouldBeInstanceOf State.Loading::class
                    }
                }
            }

            @Test
            fun `should update error Error accordingly`() {
                val params = PageKeyedDataSource.LoadParams(2, 10)
                val callback = mockk<PageKeyedDataSource.LoadCallback<Int, TestViewEntity>>(relaxed = true)

                runBlocking {

                    // Fake loading
                    failureSource.loadAfter(params, callback)

                    failureSource.reasonData.testObserve {
                        it shouldBeInstanceOf TestFailureReason::class
                    }
                }
            }
        }

        @Test
        fun `should use loadDataForPage in loadInitial and transform emmited value`() {
            val params = mockk<PageKeyedDataSource.LoadInitialParams<Int>>(relaxed = true)
            val callback = mockk<PageKeyedDataSource.LoadInitialCallback<Int, TestViewEntity>>(relaxed = true)

            // Fake loading
            source.loadInitial(params, callback)

            // Make sure load initial called only once
            verify(exactly = 1) { source.loadDataForPage(any()) }

            // Notified callback
            verify(exactly = 1) { callback.onResult(any(), any(), any()) }
        }

        @Test
        fun `should use loadDataForPage in loadAfter and transform emmited value`() {
            val params = PageKeyedDataSource.LoadParams(2, 10)
            val callback = mockk<PageKeyedDataSource.LoadCallback<Int, TestViewEntity>>(relaxed = true)

            // Fake loading
            source.loadAfter(params, callback)

            // Make sure load initial called only once
            verify(exactly = 1) { source.loadDataForPage(any()) }

            // Notified callback
            verify(exactly = 1) { callback.onResult(any(), any()) }
        }
    }

    inner class TestSource : BasePagingDataSource<TestViewEntity>() {

        val result = flow {
            emit(State.Loading())
            emit(Success(listOf(TestViewEntity(data))))
        }

        override fun loadDataForPage(page: Int): Flow<Result<List<TestViewEntity>>> = result
    }

    inner class TestFailureSource : BasePagingDataSource<TestViewEntity>() {

        val result = flow {
            emit(State.Loading())
            emit(Failure(TestFailureReason(errorMessageResId)))
        }

        override fun loadDataForPage(page: Int): Flow<Result<List<TestViewEntity>>> = result
    }

    inner class TestViewEntity(data: Int) : ViewEntity

    inner class TestFailureReason(override val messageRes: Int) : Reason()
}
