package com.hmomeni.circularprogressbar

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.hmomeni.progresscircula.dpToPx
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar.rimWidth = dpToPx(10).toFloat()

        updateBtn.setOnClickListener {
            progressBar.progress = progressValue.text.toString().toInt()
        }

        indeterminateSwitch.setOnCheckedChangeListener { _, isChecked ->
            progressBar.indeterminate = isChecked
        }

        showProgressSwitch.setOnCheckedChangeListener { _, isChecked ->
            progressBar.showProgress = isChecked
        }
        visibilitySwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked)
                progressBar.visibility = View.VISIBLE
            else
                progressBar.visibility = View.GONE
        }
    }
}
