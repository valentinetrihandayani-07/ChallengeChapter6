package com.valentine.challengech6.ui_screen.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.valentine.challengech6.data.local.repository.UserRepository


class UserHomeViewModel(private val userRepository: UserRepository) : ViewModel() {
    fun getUsername(): LiveData<String>{
        return userRepository.getUsernameValue().asLiveData()
    }
}