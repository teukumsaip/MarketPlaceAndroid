package com.teukkun.marketplace.ui.toko

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.inyongtisto.marketplace.databinding.ActivityTokoSayaBinding
import com.teukkun.marketplace.ui.alamatToko.ListAlamatTokoActivity
import com.teukkun.marketplace.ui.product.CreateProductActivity
import com.teukkun.marketplace.ui.product.ListProductTokoActivity
import com.teukkun.marketplace.util.Constants
import com.teukkun.marketplace.util.Prefs
import com.inyongtisto.myhelper.extension.getInitial
import com.inyongtisto.myhelper.extension.intentActivity
import com.inyongtisto.myhelper.extension.setToolbar
import com.squareup.picasso.Picasso

class TokoSayaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTokoSayaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTokoSayaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setToolbar(binding.lyToolbar.toolbar, "Toko Saya")
        // get data dari server

        setData()
        setupListener()
    }

    private fun setData() {
        val user = Prefs.getUser()
        if (user != null) {
            binding.apply {
                if (user.toko != null) {
                    tvName.text = user.toko?.name
                    tvInisial.text = user.toko?.name.getInitial()
                    Picasso.get().load(Constants.USER_URL + user.toko?.name)
                        .into(binding.imageProfile)
                }
            }
        }
    }

    private fun setupListener() {
        binding.apply {
            btnAlamat.setOnClickListener {
                intentActivity(ListAlamatTokoActivity::class.java)
            }

            btnListProduct.setOnClickListener {
                intentActivity(ListProductTokoActivity::class.java)
            }

            btnCreateProduct.setOnClickListener {
                intentActivity(CreateProductActivity::class.java)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}