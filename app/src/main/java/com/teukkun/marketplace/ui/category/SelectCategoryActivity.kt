package com.teukkun.marketplace.ui.category

import android.content.Intent
import android.os.Bundle
import com.inyongtisto.marketplace.databinding.ActivityListDataBinding
import com.inyongtisto.myhelper.extension.setToolbar
import com.inyongtisto.myhelper.extension.toGone
import com.inyongtisto.myhelper.extension.toJson
import com.inyongtisto.myhelper.extension.toVisible
import com.teukkun.marketplace.core.data.source.remote.network.State
import com.teukkun.marketplace.ui.base.MyActivity
import com.teukkun.marketplace.ui.category.adapter.SelectCategoryAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class SelectCategoryActivity : MyActivity() {

    private lateinit var binding: ActivityListDataBinding
    private val viewModel: CategoryViewModel by viewModel()
    private var adapter = SelectCategoryAdapter(
        onClick = {
            val intent = Intent()
            intent.putExtra("extra", it.toJson())
            setResult(RESULT_OK, intent)
            onBackPressed()
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setToolbar(binding.lyToolbar.toolbar, "List Category")

        setupUI()
        mainButton()
        getData()
        setupAdapter()
    }

    override fun onResume() {
        getData()
        super.onResume()
    }

    private fun setupUI() {
        binding.apply {

        }
    }

    private fun setupAdapter() {
        binding.rvData.adapter = adapter
    }

    private fun getData() {
        viewModel.get().observe(this) {
            when (it.state) {
                State.SUCCESS -> {
                    binding.tvError.toGone()
                    val data = it.data ?: emptyList()
                    adapter.addItems(data)

                    if (data.isEmpty()) {
                        binding.tvError.toVisible()
                    }
                }
                State.ERROR -> {
                    binding.tvError.toVisible()
                }
                State.LOADING -> {

                }
            }
        }
    }

    private fun mainButton() {

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}