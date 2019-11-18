package com.melih.core.base

import com.melih.core.BaseTestWithMainThread
import com.melih.core.base.viewmodel.BaseViewModel
import io.mockk.coVerify
import io.mockk.spyk
import org.junit.jupiter.api.Test

class BaseViewModelTest : BaseTestWithMainThread() {

    @Test
    fun `refresh should invoke loadData`() {
        val baseVm = spyk(TestViewModel())
        baseVm.loadData()

        coVerify(exactly = 1) { baseVm.loadData() }
    }

    @Test
    fun `retry should invoke loadData`() {
        val baseVm = spyk(TestViewModel())
        baseVm.loadData()

        coVerify(exactly = 1) { baseVm.loadData() }
    }
}

class TestViewModel : BaseViewModel<Unit>() {
    fun loadData() {

    }
}
