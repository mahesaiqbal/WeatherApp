package com.mahesaiqbal.weatherapp.utils

import android.os.Handler
import android.os.Looper
import androidx.annotation.VisibleForTesting
import java.util.concurrent.Executor
import java.util.concurrent.Executors

open class AppExecutors @VisibleForTesting
constructor(private val diskIO: Executor, private val networkIO: Executor, private val mainThread: Executor) {

    constructor() : this(
        DiskIOThreadExecutor(), Executors.newFixedThreadPool(THREAD_COUNT),
        MainThreadExecutor()
    )

    fun diskIO(): Executor = diskIO

    fun mainThread(): Executor = mainThread

    private class MainThreadExecutor : Executor {
        private val mainThreadHandler = Handler(Looper.getMainLooper())

        override fun execute(command: Runnable) {
            mainThreadHandler.post(command)
        }
    }

    companion object {

        private const val THREAD_COUNT = 3
    }

}
