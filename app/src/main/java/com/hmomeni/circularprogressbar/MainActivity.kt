package com.hmomeni.circularprogressbar

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressBar.progress = 5
        progressBar.textColor = Color.GREEN
        progressBar.rimColor = Color.MAGENTA

        updateBtn.setOnClickListener {
            progressBar.progress = progressValue.text.toString().toInt()
        }

        indeterminateSwitch.setOnCheckedChangeListener { _, isChecked ->
            progressBar.indeterminate = isChecked
        }

        showProgressSwitch.setOnCheckedChangeListener { _, isChecked ->
            progressBar.showProgressText = isChecked
        }
        visibilitySwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked)
                progressBar.visibility = View.VISIBLE
            else
                progressBar.visibility = View.GONE
        }
    }
}
