package com.melih.list

import com.melih.list.ui.LaunchesViewModel
import com.melih.repository.interactors.GetLaunches
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import org.junit.jupiter.api.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class LaunchesViewModelTest : BaseTestWithMainThread() {

    private val getLaunches: GetLaunches = mockk(relaxed = true)
    private val getLaunchesParams = GetLaunches.Params(15, 0)

    private val scope = CoroutineScope(Dispatchers.IO)

    @Test
    @ExperimentalCoroutinesApi
    fun `loadData should invoke getLauches with provided params`() {
        scope.launch {
            spyk(LaunchesViewModel(getLaunches, getLaunchesParams))

            // init should have called it already due to creation above
            verify(exactly = 1) { getLaunches(getLaunchesParams) }
        }
    }
}
