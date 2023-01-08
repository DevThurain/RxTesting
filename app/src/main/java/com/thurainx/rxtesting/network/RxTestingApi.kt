package com.thurainx.rxtesting.network

import com.thurainx.rxtesting.END_POINT_FACT
import com.thurainx.rxtesting.X_API_KEY
import com.thurainx.rxtesting.X_API_KEY_VALUE
import com.thurainx.rxtesting.vo.FactVO
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface RxTestingApi {

    @GET(END_POINT_FACT)
    fun getRandomFact(
        @Header(X_API_KEY) apiKey: String = X_API_KEY_VALUE,
        ) : Observable<List<FactVO>>
}