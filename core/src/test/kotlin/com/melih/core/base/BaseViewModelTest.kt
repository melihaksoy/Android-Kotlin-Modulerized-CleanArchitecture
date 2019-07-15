package com.melih.core.base

import com.melih.core.base.viewmodel.BaseViewModel
import io.mockk.coVerify
import io.mockk.spyk
import org.junit.jupiter.api.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class BaseViewModelTest {

    val baseVm = spyk(TestViewModel())

    @Test
    fun `refresh should invoke loadData`() {
        baseVm.refresh()

        coVerify(exactly = 1) { baseVm.loadData() }
    }

    @Test
    fun `retry should invoke loadData`() {
        baseVm.retry()

        coVerify(exactly = 1) { baseVm.loadData() }
    }
}

class TestViewModel : BaseViewModel<Int>() {
    override suspend fun loadData() {
        // no - op
    }
}
