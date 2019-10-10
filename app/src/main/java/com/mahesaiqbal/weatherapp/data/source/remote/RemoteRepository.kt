package com.mahesaiqbal.weatherapp.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mahesaiqbal.weatherapp.data.source.remote.response.DailyForecastResponse
import com.mahesaiqbal.weatherapp.network.Client
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class RemoteRepository {

    companion object {
        private var INSTANCE: RemoteRepository? = null

        fun getInstance(): RemoteRepository? {
            if (INSTANCE == null) {
                INSTANCE = RemoteRepository()
            }
            return INSTANCE
        }
    }

    private val apiService = Client.create()
    private val compositeDisposable = CompositeDisposable()

    val API_KEY = "dd522c8235e5b956c6637e8a72a46189"

    fun getDailyForecast(): LiveData<ApiResponse<DailyForecastResponse>> {

        val result: MutableLiveData<ApiResponse<DailyForecastResponse>> = MutableLiveData()

        apiService.getDailyForecast("Jakarta,ID", "metric", API_KEY)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<DailyForecastResponse> {
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onNext(t: DailyForecastResponse) {
                    result.postValue(ApiResponse.success(t))
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }

                override fun onComplete() {

                }
            })

        return result
    }

    fun onDestroy() {
        compositeDisposable.clear()
    }
}
