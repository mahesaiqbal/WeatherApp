package com.mahesaiqbal.weatherapp.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.mahesaiqbal.weatherapp.data.source.WeatherRepository
import com.mahesaiqbal.weatherapp.data.source.local.entity.WeatherEntity
import com.mahesaiqbal.weatherapp.vo.Resource
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class MainViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var weatherRepository: WeatherRepository

    @Mock
    lateinit var observer: Observer<Resource<List<WeatherEntity>>>

    private var viewModel: MainViewModel? = null

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = MainViewModel(weatherRepository)
    }

    @Test
    fun getDailyForecast() {
        val dummy = MutableLiveData<Resource<List<WeatherEntity>>>()
        val arrayList = mock(ArrayList::class.java) as ArrayList<WeatherEntity>

        dummy.setValue(Resource.success(arrayList))

        `when`<LiveData<Resource<List<WeatherEntity>>>>(weatherRepository.getDailyForecast()).thenReturn(dummy)

        viewModel?.getDailyForecast()?.observeForever(observer)

        verify(observer).onChanged(Resource.success(arrayList))
    }
}