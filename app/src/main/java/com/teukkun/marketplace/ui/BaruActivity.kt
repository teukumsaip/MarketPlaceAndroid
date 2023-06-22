package com.teukkun.marketplace.ui

import android.os.Bundle
import com.inyongtisto.marketplace.databinding.ActivityBukaTokoBinding
import com.teukkun.marketplace.ui.base.MyActivity
import com.teukkun.marketplace.ui.toko.TokoViewModel
import com.inyongtisto.myhelper.extension.setToolbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class BaruActivity : MyActivity() {

    private lateinit var binding: ActivityBukaTokoBinding
    private val viewModel: TokoViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBukaTokoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setToolbar(binding.lyToolbar.toolbar, "Buka Toko")

        mainButton()
    }

    private fun mainButton() {

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}