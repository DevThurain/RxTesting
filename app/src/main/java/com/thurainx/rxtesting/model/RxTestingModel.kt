package com.thurainx.rxtesting.model

import com.thurainx.rxtesting.vo.FactVO
import io.reactivex.rxjava3.core.Observable

interface RxTestingModel{

   fun getRandomFact() : Observable<List<FactVO>>
}