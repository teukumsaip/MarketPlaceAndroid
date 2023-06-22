package com.teukkun.marketplace.ui.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.inyongtisto.marketplace.databinding.ActivityRegisterBinding
import com.inyongtisto.myhelper.extension.isEmpty
import com.inyongtisto.myhelper.extension.pushActivity
import com.inyongtisto.myhelper.extension.showToast
import com.inyongtisto.myhelper.extension.toastError
import com.teukkun.marketplace.core.data.source.remote.network.State
import com.teukkun.marketplace.core.data.source.remote.request.RegisterRequest
import com.teukkun.marketplace.ui.navigation.NavigationActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterActivity : AppCompatActivity() {

    private val viewModel: AuthViewModel by viewModel()

    private var _binding: ActivityRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setData()
    }

    private fun setData() {
        binding.btnDaftar.setOnClickListener {
            register()
        }
    }

    private fun register() {

        if (binding.edtName.isEmpty()) return
        if (binding.edtPhone.isEmpty()) return
        if (binding.edtEmail.isEmpty()) return
        if (binding.edtPassword.isEmpty()) return

        val body = RegisterRequest(
            binding.edtName.text.toString(),
            binding.edtEmail.text.toString(),
            binding.edtPhone.text.toString(),
            binding.edtPassword.text.toString()
        )

        viewModel.register(body).observe(this, {

            when (it.state) {
                State.SUCCESS -> {
//                    dismisLoading()
                    showToast("Selamat datang " + it.data?.name)
                    pushActivity(NavigationActivity::class.java)
                }
                State.ERROR -> {
//                    dismisLoading()
                    toastError(it.message ?: "Error")
                }
                State.LOADING -> {
//                    showLoading()
                }
            }
        })
    }

}