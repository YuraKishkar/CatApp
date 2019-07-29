package com.example.catapp.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.catapp.R
import com.example.catapp.view.base.activity.BaseActivity

class SplashActivity : BaseActivity() {
    override fun getLayoutResId(): Int = R.layout.activity_splash

    override fun onCreate(savedInstanceState: Bundle?) {
        setSupportActionBar(findViewById(R.id.id_toolbar))
        super.onCreate(savedInstanceState)
    }
}
