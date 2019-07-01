package com.melih.list

import com.melih.list.ui.LaunchesViewModel
import com.melih.repository.interactors.GetLaunches
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class LaunchesViewModelTest : BaseTestWithMainThread() {

    val getLaunches: GetLaunches = mockk(relaxed = true)
    val getLaunchesParams: GetLaunches.Params = mockk(relaxed = true)

    @Test
    @ExperimentalCoroutinesApi
    fun `loadData should invoke getLauches with provided params`() {
        spyk(LaunchesViewModel(getLaunches, getLaunchesParams))

        dispatcher.runBlockingTest {

            // init should have called it already due to creation above
            verify(exactly = 1) { getLaunches(getLaunchesParams) }
        }
    }
}
