package com.valentine.challengech6.ui_screen.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valentine.challengech6.data.local.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class LoginViewModel(private val repository: UserRepository): ViewModel() {

    val Login : MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    fun getCekValidLogin(): LiveData<Boolean> {
        return Login
    }
    fun reset(){
       Login.value=false
    }
    fun login(username:String,password:String){
        val passwordtempt = StringBuffer()
        val usernametempt = StringBuffer()
        viewModelScope.launch(Dispatchers.IO){
            val login = repository.authLogin(username,password)
            runBlocking(Dispatchers.Main) {
                if (login != null) {
                    login.forEach {

                        passwordtempt.append(it.password.toString())
                        usernametempt.append(it.username.toString())
                    }
                    if (username==usernametempt.toString() && password==passwordtempt.toString()){
                        Login.value = true
                        viewModelScope.launch {
                            repository.setUsername(usernametempt.toString())

                        }
                    }else{
                       Login.value = true
                    }
                }
            }
        }
    }

}