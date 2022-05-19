package com.valentine.challengech6.ui_screen.register

import android.util.Log
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.*
import androidx.navigation.fragment.findNavController
import com.valentine.challengech6.R
import com.valentine.challengech6.data.local.repository.UserRepository
import com.valentine.challengech6.data.local.user.User
import com.valentine.challengech6.data.local.user.UserDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class RegisterViewModel(private val repository: UserRepository): ViewModel(){

val register:
        MutableLiveData<Boolean> by lazy {
            MutableLiveData <Boolean>()
}
    fun getRegister(): LiveData<Boolean> {
        return register

    }
    fun reset(){
        register.postValue(false)
    }
    fun userRegister(userDatabase: User, cekPassword: String ){
        viewModelScope.launch (Dispatchers.IO)
        {
            val result = repository.userRegister(userDatabase)
            Log.d("test", result.toString())
            runBlocking (Dispatchers.Main){
                if (result!=0 .toLong()){
                    register.postValue(true)
                }
            }

        }
    }

}