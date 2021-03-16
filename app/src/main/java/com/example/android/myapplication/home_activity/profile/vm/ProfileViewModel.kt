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

    private var _savedName = MutableLiveData<String?>()
    val savedName: LiveData<String?>
          get()= _savedName

    fun saveName (name:String){
        viewModelScope.launch(Dispatchers.IO) {
            repository.save(name)
        }
    }

    fun readName(){
        viewModelScope.launch(Dispatchers.IO) {
            _savedName.postValue(repository.readName())
        }
    }
    }