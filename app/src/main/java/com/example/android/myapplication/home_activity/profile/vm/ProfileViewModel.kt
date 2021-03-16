package com.example.android.myapplication.home_activity.profile.vm

import androidx.datastore.preferences.core.preferencesKey
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.data.repositories.TransactionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: TransactionRepository): ViewModel() {

    private var _profile = MutableLiveData<ProfileData>()
    val profile: LiveData<ProfileData>
          get()= _profile

    init{
        viewModelScope.launch(Dispatchers.IO) {
            val name = repository.readName().orEmpty()
            val lastName = repository.readLastName().orEmpty()
            val profileData = ProfileData (name, lastName)
            _profile.postValue(profileData)
        }
    }
    fun saveName (name:String){
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveName(name)
        }
    }
    fun saveLastName (lastName:String){
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveLastName(lastName)
        }
    }
    data class ProfileData (val name:String, val lastName:String)
}
