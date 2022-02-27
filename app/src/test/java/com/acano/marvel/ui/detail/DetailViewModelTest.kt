package com.acano.marvel.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
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

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    val testCoroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        viewModel = DetailViewModel()
    }


    @Test
    fun `test not exist id hero`() {
        testCoroutineRule.testDispatcher.runBlockingTest {
            viewModel.submitHero(-1)
        }
        assert(viewModel.hero.value == null)
    }
    @Test
    fun `test exist id hero`() {
        testCoroutineRule.testDispatcher.runBlockingTest {
            viewModel.submitHero(1011334)
        }
        assert(viewModel.hero.value != null)
    }
}