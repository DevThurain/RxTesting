package com.thurainx.rxtesting

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import dev.shreyaspatil.MaterialDialog.MaterialDialog
import kotlinx.android.synthetic.main.activity_next.*


class NextActivity : AppCompatActivity() {
    companion object {
        fun getIntent(context: Context): Intent {
            val intent = Intent(context, NextActivity::class.java)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next)

        setUpLiveData()
        btnGoBack.setOnClickListener {
            finish()
        }

        btnShow.setOnClickListener {
            showDialog()
        }
    }

    private fun setUpLiveData(){
//        RxTestingApplication.callFacts()
        RxTestingApplication.factLiveData.observe(this) {
            tvPrint2.text = it
        }
        RxTestingApplication.errorLiveData.observe(this) {
            if(it.isNotEmpty()){
                Snackbar.make(window.decorView,it,Toast.LENGTH_SHORT).show()
                RxTestingApplication.clearError()
            }
        }
    }

    private fun showDialog(){
        val mDialog = MaterialDialog.Builder(this)
            .setTitle("Delete?")
            .setMessage("Are you sure want to delete this file?")
            .setCancelable(false)
            .setPositiveButton(
                "Delete", R.drawable.ic_close
            ) { dialogInterface, which ->
                // Delete Operation
            }
            .setNegativeButton(
                "Cancel", R.drawable.ic_close
            ) { dialogInterface, which -> dialogInterface.dismiss() }
            .build()

        // Show Dialog

        // Show Dialog
        mDialog.show()
    }
}