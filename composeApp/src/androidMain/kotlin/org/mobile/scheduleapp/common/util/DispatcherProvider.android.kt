package org.mobile.scheduleapp.common.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

internal class AndroidDispatcher: DispatcherProvider {
    override val io: CoroutineDispatcher
        get() = Dispatchers.IO
    override val main: CoroutineDispatcher
        get() = Dispatchers.Main
}

internal actual fun provideDispatcher(): DispatcherProvider = AndroidDispatcher()