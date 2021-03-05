package com.beeline.demo.cocktailapp.ui.launcher

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.beeline.demo.cocktailapp.ui.base.BaseActivity
import com.beeline.demo.cocktailapp.R

class PreviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_preview)
        Handler(Looper.getMainLooper()).postDelayed({ startNecessaryActivity() }, 2000)
    }

    private fun startNecessaryActivity() {
        val intent =
            Intent(this, BaseActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }
}