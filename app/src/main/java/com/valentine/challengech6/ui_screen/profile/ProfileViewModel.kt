package com.valentine.challengech6.ui_screen.profile

import androidx.lifecycle.*
import com.valentine.challengech6.data.local.repository.UserRepository
import com.valentine.challengech6.data.local.user.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ProfileViewModel (private val repository: UserRepository): ViewModel() {
    private val user : MutableLiveData<User> by lazy {
        MutableLiveData<User>()
    }
    val updateValidation : MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    fun getUpdateValidation(): LiveData<Boolean> {
        return updateValidation
    }
    fun resultUser(): LiveData<User> {
        return user
    }
    //    fun result():LiveData<Boolean>{
//        return
//    }
    fun getUsername(): LiveData<String> {
        return repository.getUsernameValue().asLiveData()
    }
    fun getEmail(): LiveData<String> {
        return repository.getEmailValue().asLiveData()
    }
    fun setUsername(username:String){
        viewModelScope.launch {
            repository.setUsername(username)
        }
    }
    fun logOut(){
        viewModelScope.launch {
            repository.clearDataStore()
        }
    }
    fun getPhoto(username: String){
        val photoResult = StringBuffer()
        viewModelScope.launch(Dispatchers.IO){
 /*           val result = repository.getPhoto(username)*/
            runBlocking(Dispatchers.IO){
               /* result.let {
                    photoResult.append(it.imagePath)
                }*/

            }
        }
    }

    fun getUserData(username:String) {
        val usernameResult = StringBuffer()
        val fullnameResult = StringBuffer()
        val emailResult = StringBuffer()
        val imageResult     = StringBuffer()
        viewModelScope.launch(Dispatchers.IO){
            val result = repository.getAllData(username = username)
            runBlocking(Dispatchers.Main) {
                result?.let {
                    usernameResult.append(it.username.toString())
                    fullnameResult.append(it.fullname.toString())
                    emailResult.append(it.email.toString())
                    imageResult.append(it.imagepath.toString())
                }
                val resultDataUser = User(
                    username    = usernameResult.toString(),
                    fullname    = fullnameResult.toString(),
                    email       = emailResult.toString(),
                    imagepath = imageResult.toString()
                )
                user.value = resultDataUser
            }
        }
    }

    fun updateData(userData: User,email: String){
        viewModelScope.launch(Dispatchers.IO){
            val result = repository.updateProfile(
                userData,
            )
            runBlocking(Dispatchers.Main){
                if (result != 0){
                    updateValidation.postValue(true)
                }
            }
        }
    }
}