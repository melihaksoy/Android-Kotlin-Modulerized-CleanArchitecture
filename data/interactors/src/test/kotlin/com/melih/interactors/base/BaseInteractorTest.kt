package com.melih.repository.interactors.base

import com.melih.abstractions.deliverable.Result
import com.melih.abstractions.deliverable.State
import com.melih.abstractions.deliverable.Success
import io.mockk.coVerify
import io.mockk.spyk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.toCollection
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldBeInstanceOf
import org.amshove.kluent.shouldEqualTo
import org.junit.jupiter.api.Test
import java.util.ArrayDeque

@UseExperimental(ExperimentalCoroutinesApi::class)
class BaseInteractorTest {

    val testInteractor = spyk(TestInteractor())
    val testParams = TestParams()

    @Test
    fun `BaseInteractor should send states and items emmited by run`() {
        // Using run blocking due to threading problems in runBlockingTest
        // See https://github.com/Kotlin/kotlinx.coroutines/issues/1204

        runBlocking {
            // Get result by invoking
            val result = testInteractor(testParams)

            // Verify we invoked interactor exactly once
            coVerify(exactly = 1) { testInteractor.invoke(any()) }

            // Verify result is type of Flow
            result shouldBeInstanceOf Flow::class

            // This will actually collec the flow
            val resultDeque = ArrayDeque<Result<Int>>()
            result.toCollection(resultDeque)

            // We sent exactly 3 items, verify size
            resultDeque.size shouldEqualTo 3

            // Verify first item is Loading state
            resultDeque.poll() shouldBeInstanceOf State.Loading::class

            // Verify second item is Success, with default value we set below in TestParams class
            resultDeque.poll().also {
                it shouldBeInstanceOf Success::class
                (it as Success<Int>).successData shouldEqualTo 10
            }

            // Verify last item is Loaded state
            resultDeque.poll() shouldBeInstanceOf State.Loaded::class
        }
    }

    inner class TestInteractor : BaseInteractor<Int, TestParams>() {

        override suspend fun FlowCollector<Result<Int>>.run(params: TestParams) {
            emit(Success(params.testValue))
        }
    }

    data class TestParams(val testValue: Int = 10) : InteractorParameters
}