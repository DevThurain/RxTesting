package com.thurainx.rxtesting.connectivity

import android.net.ConnectivityManager
import com.thurainx.rxtesting.utils.currentConnectivityState
import com.thurainx.rxtesting.utils.observeConnectivityAsObservable
import io.reactivex.rxjava3.core.Observable

class ConnectivityObserverImpl(
    private val connectivityManager: ConnectivityManager
) : ConnectivityObserver {

    override val connectionState: Observable<ConnectionState>
        get() = connectivityManager.observeConnectivityAsObservable()

    override val currentConnectionState: ConnectionState
        get() = connectivityManager.currentConnectivityState
}