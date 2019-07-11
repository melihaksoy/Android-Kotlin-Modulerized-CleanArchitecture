package com.melih.list

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

abstract class BaseTestWithMainThread {

    @ExperimentalCoroutinesApi
    protected val dispatcher = TestCoroutineDispatcher()

    @BeforeEach
    @ExperimentalCoroutinesApi
    fun setUp() {
        Dispatchers.setMain(dispatcher)
    }

    @AfterEach
    @ExperimentalCoroutinesApi
    fun tearDown() {
        Dispatchers.resetMain()
        dispatcher.cleanupTestCoroutines()
    }
}
