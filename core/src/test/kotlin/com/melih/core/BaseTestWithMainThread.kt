package com.melih.core

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import androidx.lifecycle.LiveData
import com.melih.core.observers.OneShotObserverWithLifecycle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import java.util.concurrent.Executors
import kotlin.coroutines.suspendCoroutine

abstract class BaseTestWithMainThread {
    private val dispatcher = Executors.newSingleThreadExecutor().asCoroutineDispatcher()

    @BeforeEach
    @ExperimentalCoroutinesApi
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        ArchTaskExecutor.getInstance()
            .setDelegate(object : TaskExecutor() {
                override fun executeOnDiskIO(runnable: Runnable) = runnable.run()

                override fun isMainThread(): Boolean = true

                override fun postToMainThread(runnable: Runnable) = runnable.run()
            })
    }

    @AfterEach
    @ExperimentalCoroutinesApi
    fun tearDown() {
        Dispatchers.resetMain()
        dispatcher.close()
        ArchTaskExecutor.getInstance()
            .setDelegate(null)
    }
}

suspend fun <T> LiveData<T>.testObserve(onChangeHandler: (T) -> Unit) {
    suspendCoroutine<Unit> {
        val observer = OneShotObserverWithLifecycle(onChangeHandler, it)
        observe(observer, observer)
    }
}
