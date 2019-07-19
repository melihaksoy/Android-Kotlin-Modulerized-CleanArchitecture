package com.melih.repository.sources

import android.content.Context
import com.melih.repository.entities.LaunchEntity
import com.melih.repository.interactors.base.Reason
import com.melih.repository.interactors.base.Result
import com.melih.repository.persistence.LaunchesDatabase
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.spyk
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldBeInstanceOf
import org.amshove.kluent.shouldEqualTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class PersistanceSourceTest {

    private val ctx = mockk<Context>(relaxed = true)
    private val dbImplementation = mockk<LaunchesDatabase>(relaxed = true)
    private val source = spyk(PersistenceSource(ctx))

    private val scope = CoroutineScope(Dispatchers.IO)

    @BeforeEach
    fun setup() {
        mockkObject(LaunchesDatabase)
        every { LaunchesDatabase.getInstance(ctx) } returns dbImplementation
    }

    @Nested
    inner class GetNextLaunches {

        @Test
        @ExperimentalCoroutinesApi
        fun `should return persistance empty error when db is empty`() {
            coEvery { dbImplementation.launchesDao.getLaunches(any(), any()) } returns emptyList()

            scope.launch {
                val result = source.getNextLaunches(10, 0)

                result shouldBeInstanceOf Result.Failure::class
                result.onFailure {
                    it shouldBeInstanceOf Reason.PersistenceEmpty::class
                }
            }
        }

        @Test
        @ExperimentalCoroutinesApi
        fun `should return success with data if db is not empty`() {
            coEvery { dbImplementation.launchesDao.getLaunches(any(), any()) } returns listOf(LaunchEntity(id = 1013))

            scope.launch {
                val result = source.getNextLaunches(10, 0)

                result shouldBeInstanceOf Result.Success::class
                result.onSuccess {
                    it.isEmpty() shouldBe false
                    it.size shouldEqualTo 1
                    it[0].id shouldEqualTo 1013
                }
            }
        }
    }
}
