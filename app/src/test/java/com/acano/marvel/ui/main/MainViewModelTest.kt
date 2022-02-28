package com.acano.marvel.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @Mock
    private lateinit var viewModel: MainViewModel

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    val testCoroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        viewModel = MainViewModel(get())
    }

    @Test
    fun `test getting data for hero`() {

        assert(!viewModel.heroList.value?.get(0)?.name.equals("Hero 1"))
    }
}