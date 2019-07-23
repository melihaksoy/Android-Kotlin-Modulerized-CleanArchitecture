package com.melih.core.paging

import com.melih.core.BaseTestWithMainThread
import com.melih.core.base.paging.BasePagingDataSource
import com.melih.core.base.paging.BasePagingFactory
import com.melih.core.testObserve
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldEqual
import org.junit.jupiter.api.Test

class BasePagingFactoryTest : BaseTestWithMainThread() {

    val factory = spyk(TestFactory())

    @Test
    fun `create should update current source when it creates a new one`() {
        val source = factory.create()

        runBlocking {
            factory.currentSource.testObserve {
                it shouldEqual source
            }
        }
    }

    inner class TestFactory : BasePagingFactory<String>() {

        override fun createSource(): BasePagingDataSource<String> = mockk(relaxed = true)

    }
}
