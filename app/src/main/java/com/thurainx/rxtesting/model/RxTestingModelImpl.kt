package com.thurainx.rxtesting.model

import com.thurainx.rxtesting.vo.FactVO
import io.reactivex.rxjava3.core.Observable

object RxTestingModelImpl : RxTestingModel, BasedModel() {
    override fun getRandomFact(): Observable<List<FactVO>> {
        return mRxTestingApi.getRandomFact()
    }


}