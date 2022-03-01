package com.acano.marvel.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.acano.marvel.network.RetrofitDataSource
import com.acano.marvel.repository.DataRepository
import com.acano.marvel.repository.RemoteDataSource
import com.acano.marvel.ui.main.MainCoroutineRule
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    @Mock
    private lateinit var viewModel: DetailViewModel
    @Mock
    private lateinit var repo: DataRepository
    @Mock
    private lateinit var  remoteDataSource: RemoteDataSource
    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    val testCoroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        remoteDataSource= RetrofitDataSource()
        repo=DataRepository(remoteDataSource)
        viewModel = DetailViewModel(repo)
    }

    @Test
    fun `test exist id hero`() {
        testCoroutineRule.testDispatcher.runBlockingTest {
            viewModel.submitHero(1011334)
        }
        val resultId:Int=1011334
        assert(true)to (viewModel.hero.value?.id == resultId)
    }
    @Test
    fun `test not exist id hero`() {
        testCoroutineRule.testDispatcher.runBlockingTest {
            viewModel.submitHero(-1)
        }
        assert(viewModel.hero.value == null)
    }

}