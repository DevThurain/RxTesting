package com.thurainx.rxtesting

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_transform.*

class TransformActivity : AppCompatActivity() {
    companion object {
        fun getIntent(context: Context): Intent {
            val intent = Intent(context, TransformActivity::class.java)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transform)

        fab.setOnClickListener {
            transformationLayout.startTransform()
        }

        myCardView.setOnClickListener {
            transformationLayout.finishTransform()
        }
    }
}