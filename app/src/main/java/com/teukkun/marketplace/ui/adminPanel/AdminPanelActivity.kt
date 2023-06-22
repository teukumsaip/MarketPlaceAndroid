package com.teukkun.marketplace.ui.adminPanel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.inyongtisto.marketplace.databinding.ActivityAdminPanelBinding
import com.teukkun.marketplace.ui.category.ListCategoryAdminActivity
import com.teukkun.marketplace.ui.slider.ListSliderAdminActivity
import com.inyongtisto.myhelper.extension.intentActivity
import com.inyongtisto.myhelper.extension.setToolbar

class AdminPanelActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdminPanelBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminPanelBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setToolbar(binding.lyToolbar.toolbar, "Admin Panel")
        // get data dari server

        setData()
        setupListener()
    }

    private fun setData() {

    }

    private fun setupListener() {
        binding.apply {
            btnCategory.setOnClickListener {
                intentActivity(ListCategoryAdminActivity::class.java)
            }

            btnSlider.setOnClickListener {
                intentActivity(ListSliderAdminActivity::class.java)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}