package com.echavez.labo3evaluado.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.echavez.labo3evaluado.Network.movieApi
import com.echavez.labo3evaluado.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
