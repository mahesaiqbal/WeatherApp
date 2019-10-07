package com.mahesaiqbal.weatherapp.data.source

import androidx.lifecycle.LiveData
import com.mahesaiqbal.weatherapp.data.source.local.LocalRepository
import com.mahesaiqbal.weatherapp.data.source.local.entity.WeatherEntity
import com.mahesaiqbal.weatherapp.data.source.remote.ApiResponse
import com.mahesaiqbal.weatherapp.data.source.remote.RemoteRepository
import com.mahesaiqbal.weatherapp.data.source.remote.response.DailyForecastResponse
import com.mahesaiqbal.weatherapp.data.source.remote.response.X
import com.mahesaiqbal.weatherapp.utils.AppExecutors
import com.mahesaiqbal.weatherapp.vo.Resource

class WeatherRepository(
    var localRepository: LocalRepository,
    var remoteRepository: RemoteRepository,
    var appExecutors: AppExecutors
) : WeatherDataSource {

    companion object {
        @Volatile
        private var INSTANCE: WeatherRepository? = null

        fun getInstance(
            localRepository: LocalRepository,
            remoteData: RemoteRepository,
            appExecutor: AppExecutors
        ): WeatherRepository? {
            if (INSTANCE == null) {
                synchronized(WeatherRepository::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = WeatherRepository(localRepository, remoteData, appExecutor)
                    }
                }
            }
            return INSTANCE
        }
    }

    override fun getAllPopularMovies(): LiveData<Resource<PagedList<PopularMovieEntity>>> {
        return object : NetworkBoundResource<PagedList<PopularMovieEntity>, MutableList<ResultPopularMovie>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<PopularMovieEntity>> {
                return LivePagedListBuilder(localRepository.getAllPopularMovie(), 10).build()
            }

            override fun shouldFetch(data: PagedList<PopularMovieEntity>): Boolean {
                return data == null || data.size == 0
            }

            override fun createCall(): LiveData<ApiResponse<MutableList<ResultPopularMovie>>>? {
                return remoteRepository.getAllPopularMovie()
            }

            override fun saveCallResult(data: MutableList<ResultPopularMovie>?) {
                val popularMovies = mutableListOf<PopularMovieEntity>()

                for (i in data!!.indices) {
                    val response: ResultPopularMovie = data[i]
                    val (id, backdropPath, originalLanguage,
                        overview, popularity, posterPath,
                        releaseDate, title, voteAverage,
                        voteCount) = response
                    val popularMovie = PopularMovieEntity(id, backdropPath, originalLanguage,
                        overview, popularity, posterPath, releaseDate, title, voteAverage,
                        voteCount, false)

                    popularMovies.add(popularMovie)
                }

                localRepository.insertPopularMovie(popularMovies)
            }
        }.asLiveData()
    }

    override fun getDailyForecast(): LiveData<Resource<List<WeatherEntity>>> {
        return object : NetworkBoundResource<List<WeatherEntity>, DailyForecastResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<List<WeatherEntity>> {
                return localRepository.getDailyForecast()
            }

            override fun shouldFetch(data: List<WeatherEntity>): Boolean {
                return data == null || data.size == 0
            }

            override fun createCall(): LiveData<ApiResponse<DailyForecastResponse>>? {
                return remoteRepository.getDailyForecast()
            }

            override fun saveCallResult(data: DailyForecastResponse?) {
                val dailyForecast = listOf<WeatherEntity>()

                for (i in data!!.list) {
                    val response: X = data.list

                    //Define destructuring declarations


                    popularMovies.add(popularMovie)
                }

                localRepository.insertDailyForecast(dailyForecast)
            }

        }.asLiveData()
    }
}
