package com.melih.detail

import com.melih.detail.ui.DetailViewModel
import com.melih.repository.interactors.GetLaunchDetails
import io.mockk.mockk
import io.mockk.slot
import io.mockk.spyk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.amshove.kluent.shouldEqualTo
import org.junit.jupiter.api.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@UseExperimental(ExperimentalCoroutinesApi::class)
class DetailViewModelTest : BaseTestWithMainThread() {

    private val getLaunchDetails: GetLaunchDetails = mockk(relaxed = true)
    private val getLaunchDetailsParams = GetLaunchDetails.Params(1013)

    private val viewModel = spyk(DetailViewModel(getLaunchDetails, getLaunchDetailsParams))

    @Test
    fun `loadData should invoke getLauchDetails with provided params`() {
        dispatcher.runBlockingTest {

            val paramsSlot = slot<GetLaunchDetails.Params>()

            viewModel.loadData()

            // init should have called it already due to creation above
            verify(exactly = 1) { getLaunchDetails(capture(paramsSlot)) }
            paramsSlot.captured.id shouldEqualTo 1013
        }
    }
}
