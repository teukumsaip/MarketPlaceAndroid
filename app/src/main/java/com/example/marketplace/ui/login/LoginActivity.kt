package com.example.marketplace.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.marketplace.R
import com.example.marketplace.databinding.ActivityLoginBinding
import com.example.marketplace.databinding.FragmentDashboardBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.example.marketplace.util.Prefs as Prefs

@Suppress("DEPRECATION")
class LoginActivity : AppCompatActivity() {

    private val viewModel : LoginViewModel by viewModel()

    private var _binding: ActivityLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("RESPOM", "PESAN SINGKAT")

        setData()
    }

    fun setData(){
        viewModel.text.observe(this, {
            binding.edtEmail.setText(it)
        })

        binding.btnMasuk.setOnClickListener{
            viewModel.ubahData()
        }
    }

    fun testing(){
        val s = Prefs(this)
        if (s.getIsLogin()) {
            binding.tvStatus.text = "SUDAH LOGIN"
        } else binding.tvStatus.text = "BELUM LOGIN"

        binding.btnLogin.setOnClickListener {
            s.setIsLogin(true)
            onBackPressed()
        }

        binding.btnLogout.setOnClickListener {
            s.setIsLogin(false)
            onBackPressed()
        }
    }

}