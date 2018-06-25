package com.hmomeni.circularprogressbar

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressBar.setOnClickListener {
            if (progressBar.isRotating) {
                progressBar.stopRotation()
            } else {
                progressBar.startRotation()
            }
        }
    }
}
