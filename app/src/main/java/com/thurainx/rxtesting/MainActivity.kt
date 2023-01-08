package com.thurainx.rxtesting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.thurainx.rxtesting.RxTestingApplication.Companion.callFacts
import com.thurainx.rxtesting.RxTestingApplication.Companion.factLiveData
import com.thurainx.rxtesting.connectivity.ConnectivityObserverImpl
import com.thurainx.rxtesting.utils.connectivityManager
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    var total : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpLiveData()
        btnNext.setOnClickListener {
            val intent = NextActivity.getIntent(this)
            startActivity(intent)
        }
        btnTransform.setOnClickListener {
            val intent = TransformActivity.getIntent(this)
            startActivity(intent)
        }

        val connectivityObserverImpl = ConnectivityObserverImpl(this.connectivityManager)

        connectivityObserverImpl.connectionState
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d("connection",it.toString())
            }
    }

    override fun onStart() {
        Log.d("state","restart")
        super.onStart()
    }

    override fun onResume() {
        Log.d("state","resume")
        super.onResume()
    }

    override fun onRestart() {
        Log.d("state","restart")
        super.onRestart()
    }

    override fun onStop() {
        Log.d("state","stop")
        super.onStop()
    }

    override fun onPause() {
        Log.d("state","pause")
        super.onPause()
    }

    override fun onDestroy() {
        Log.d("state","destroy")
        super.onDestroy()
    }

//    private fun setUpRx(){
//        val disposableObservable  = Observable.interval(5, TimeUnit.SECONDS)
//            .flatMap { along -> Observable.just(5) }
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(
//                {
//                    total += 5
//                tvPrint.text = "$total seconds"
//                    Log.d("counter","$total seconds")
//
//
//                },
//                {
//                    Log.d("error",it.message ?: "cannot print")
//                }
//            )
//    }

    private fun setUpLiveData(){
//        callFacts()
        factLiveData.observe(this) {
            tvPrint.text = it
        }
        RxTestingApplication.errorLiveData.observe(this) {
            if(it.isNotEmpty()){
                Snackbar.make(window.decorView,it,Toast.LENGTH_SHORT).show()
                RxTestingApplication.clearError()
            }
        }
    }
}