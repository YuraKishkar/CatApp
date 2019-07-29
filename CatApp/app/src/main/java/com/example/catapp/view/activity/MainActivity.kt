package com.example.catapp.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.catapp.R
import com.example.catapp.view.base.activity.BaseActivity

class MainActivity : BaseActivity() {
    override fun getLayoutResId(): Int = R.layout.activity_main
    override fun onCreate(savedInstanceState: Bundle?) {
        setSupportActionBar(findViewById(R.id.id_toolbar))
        super.onCreate(savedInstanceState)
    }
}
