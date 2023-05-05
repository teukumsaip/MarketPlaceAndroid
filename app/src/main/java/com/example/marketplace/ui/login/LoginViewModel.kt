package com.example.marketplace.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marketplace.core.data.repository.AppRepository

class LoginViewModel(val repo:AppRepository) : ViewModel(){

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    fun ubahData(){
        _text.postValue("Berubah kan")
    }

}
