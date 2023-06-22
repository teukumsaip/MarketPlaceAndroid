package com.teukkun.marketplace

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.inyongtisto.marketplace.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("RESPON", "PESAN SINGKAT")


        // get data dari server
    }
}