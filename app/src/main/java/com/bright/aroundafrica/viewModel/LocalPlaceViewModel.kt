package com.bright.aroundafrica.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bright.aroundafrica.api.BaseNetwork
import com.bright.aroundafrica.model.LocalPlace
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class LocalPlaceViewModel : ViewModel() {

    val localPlace: MutableLiveData<LocalPlace?> = MutableLiveData()
    val localPlaceList: MutableLiveData<List<LocalPlace>> = MutableLiveData()

    fun getLocalPlace(id: String) {
        viewModelScope.launch {
            try {
                localPlace.value = BaseNetwork.retrofitLocalPlace.getPlace(id)
            } catch (e: Exception) {
                localPlace.value = null
            }

        }
    }

    fun getLocalPlaces() {
        viewModelScope.launch {
            localPlaceList.value = BaseNetwork.retrofitLocalPlace.getPlaces()
        }
    }
}