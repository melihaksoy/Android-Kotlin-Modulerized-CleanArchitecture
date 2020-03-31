package com.melih.detail

import androidx.lifecycle.viewModelScope
import com.melih.detail.ui.DetailViewModel
import com.melih.interactors.GetLaunchDetails
import com.melih.launches.data.LaunchDetailItem
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.spyk
import io.mockk.verify
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.amshove.kluent.shouldEqualTo
import org.junit.jupiter.api.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class DetailViewModelTest : BaseTestWithMainThread() {

    private val getLaunchDetails: GetLaunchDetails<LaunchDetailItem> = mockk(relaxed = true)
    private val getLaunchDetailsParams = GetLaunchDetails.Params(1013)

    private val viewModel = spyk(DetailViewModel(getLaunchDetails, getLaunchDetailsParams))
}
