package com.bright.aroundafrica.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bright.aroundafrica.api.BaseNetwork
import com.bright.aroundafrica.model.LocalPlace
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class QRViewModel : ViewModel() {

    val qrCode: MutableLiveData<String?> = MutableLiveData()


    fun createQr(payload: String) {
        viewModelScope.launch {
            qrCode.value = BaseNetwork.retrofitQRCodeApi.generateQrCode(payload)
        }
    }
}