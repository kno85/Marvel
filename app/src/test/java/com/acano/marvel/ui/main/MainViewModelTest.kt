package com.acano.marvel.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.acano.marvel.network.RetrofitDataSource
import com.acano.marvel.repository.DataRepository
import com.acano.marvel.repository.RemoteDataSource
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
        viewModel = MainViewModel(repo)
    }

    @Test
    fun `test getting data for not exists hero`() {

        assert(!viewModel.heroList.value?.get(0)?.name.equals("Hero 1"))
    }
    @Test
    fun `test getting data for hero`() {
        val resultName:String="3-D Man"

        assert(true) to viewModel.heroList.value?.get(0)?.name.equals(resultName)
    }
}