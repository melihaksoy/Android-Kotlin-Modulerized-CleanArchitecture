package com.melih.repository.interactors.base

import com.melih.repository.R
import io.mockk.called
import io.mockk.spyk
import io.mockk.verify
import org.amshove.kluent.shouldBeInstanceOf
import org.amshove.kluent.shouldEqualTo
import org.junit.jupiter.api.Test

class ResultTest {

    private val number = 10

    private val success = Success(number)
    private val failure = Failure(GenericError())
    private val state = State.Loading()

    private val emptyStateBlock = spyk({ _: State -> })
    private val emptyFailureBlock = spyk({ _: Reason -> })
    private val emptySuccessBlock = spyk({ _: Int -> })

    @Test
    fun `Success should only invoke successBlock with correct data`() {
        val actualSuccessBlock = spyk({ data: Int ->
            data shouldEqualTo number
            Unit
        })

        success.handle(emptyStateBlock, emptyFailureBlock, actualSuccessBlock)

        verify { emptyStateBlock wasNot called }
        verify { emptyFailureBlock wasNot called }
        verify(exactly = 1) { actualSuccessBlock.invoke(any()) }
    }

    @Test
    fun `Failure should only invoke failureBlock with correct error`() {
        val actualFailureBlock = spyk({ reason: Reason ->
            reason shouldBeInstanceOf GenericError::class
            (reason as GenericError).messageRes shouldEqualTo R.string.reason_generic
            Unit
        })

        failure.handle(emptyStateBlock, actualFailureBlock, emptySuccessBlock)

        verify { emptySuccessBlock wasNot called }
        verify { emptyStateBlock wasNot called }
        verify(exactly = 1) { actualFailureBlock.invoke(any()) }
    }

    @Test
    fun `State should only invoke stateBlock with correct state`() {
        val actualSuccessBlock = spyk({ state: State ->
            state shouldBeInstanceOf State.Loading::class
            Unit
        })

        state.handle(actualSuccessBlock, emptyFailureBlock, emptySuccessBlock)

        verify { emptySuccessBlock wasNot called }
        verify { emptyFailureBlock wasNot called }
        verify(exactly = 1) { actualSuccessBlock.invoke(any()) }
    }
}
