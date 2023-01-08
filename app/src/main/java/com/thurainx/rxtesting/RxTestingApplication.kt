package com.thurainx.rxtesting

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.thurainx.rxtesting.model.RxTestingModelImpl
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response
import java.util.concurrent.TimeUnit

class RxTestingApplication : Application() {
    companion object{
        var disposableObservable : Disposable? = null
        var factDisposableObservable: Disposable? = null
        var total = 0
        val factLiveData: MutableLiveData<String> by lazy {
            MutableLiveData<String>()
        }
        val errorLiveData: MutableLiveData<String> by lazy {
            MutableLiveData<String>()
        }

        fun startCounter(){
            Log.d("counter","start ...")
            disposableObservable  = Observable.interval(5, TimeUnit.SECONDS)
                .flatMap { along -> Observable.just(10) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(
                     {
                         total += 5
//                         tvPrint.text = "$total seconds"
                         Log.d("counter","$total seconds")

                     },
                     {
                         Log.d("error",it.message ?: "cannot print")
                     }
                 )
        }

        fun stopCounter(){
            disposableObservable?.let {
                it.dispose()
            }
            Log.d("counter","stopped ...")

        }

        fun toggle(){
            if(disposableObservable == null){
                startCounter()
            }else if(!disposableObservable!!.isDisposed){
                stopCounter()
            }else{
                startCounter()
            }
        }

        fun callFacts(){
            factDisposableObservable  = Observable.interval(5, TimeUnit.SECONDS)
                .flatMap { along -> RxTestingModelImpl.getRandomFact() }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        total++
                        if(it.isNotEmpty()){
                            factLiveData.value = it.first().fact
                            Log.d("fact",it.first().fact.toString())
                        }
//                        if(total == 3){
//                            throw HttpException(Response.error<ResponseBody>(404,ResponseBody.create("plain/text".toMediaType(),"")))
//                        }
                    },
                    {
                        if(it is HttpException){
                            errorLiveData.postValue(it.code().toString())
                        }
                    }
                )
        }

        fun clearError(){
            errorLiveData.value = ""
        }


    }



    override fun onCreate() {
        super.onCreate()


    }



}