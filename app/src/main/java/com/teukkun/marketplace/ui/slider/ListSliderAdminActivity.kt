package com.teukkun.marketplace.ui.slider

import android.os.Bundle
import com.teukkun.marketplace.core.data.source.model.Slider
import com.teukkun.marketplace.core.data.source.remote.network.State
import com.inyongtisto.marketplace.databinding.ActivityListDataBinding
import com.teukkun.marketplace.ui.base.MyActivity
import com.teukkun.marketplace.ui.slider.adapter.SliderAdminAdapter
import com.teukkun.marketplace.util.defaultError
import com.inyongtisto.myhelper.extension.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListSliderAdminActivity : MyActivity() {

    private lateinit var binding: ActivityListDataBinding
    private val viewModel: SliderViewModel by viewModel()
    private var adapter = SliderAdminAdapter(
        onClick = {
            intentActivity(CreateSliderActivity::class.java, it)
        }, onDelete = { item, pos ->
            confirmDelete(item, pos)
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setToolbar(binding.lyToolbar.toolbar, "List Slider")

        setupUI()
        mainButton()
        getData()
        setupAdapter()
    }

    override fun onResume() {
        getData()
        super.onResume()
    }

    private fun confirmDelete(item: Slider, pos: Int) {
        showConfirmDialog(
            "Delete Slider",
            "Apakah anda yakin ingin menghapus Category ini?",
            "Delete"
        ) {
            onDelete(item, pos)
        }
    }

    private fun onDelete(item: Slider, pos: Int) {
        viewModel.delete(item.id).observe(this) {
            when (it.state) {
                State.SUCCESS -> {
                    adapter.removeAt(pos)
                    progress.dismiss()
                    toastSuccess("Alamat berhasil di hapus")
                }
                State.ERROR -> {
                    showErrorDialog(it.message.defaultError())
                    progress.dismiss()
                }
                State.LOADING -> {
                    progress.show()
                }
            }
        }
    }

    private fun setupUI() {
        binding.apply {
            lyToolbar.btnTambah.toVisible()
            lyToolbar.btnTambah.setOnClickListener {
                intentActivity(CreateSliderActivity::class.java)
            }
        }
    }

    private fun setupAdapter() {
        binding.rvData.adapter = adapter
    }

    private fun getData() {
        viewModel.get().observe(this) {
            when (it.state) {
                State.SUCCESS -> {
                    binding.swipeRefresh.dismissLoading()
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
        binding.apply {
            swipeRefresh.setDefaultColor()
            swipeRefresh.setOnRefreshListener {
                getData()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}