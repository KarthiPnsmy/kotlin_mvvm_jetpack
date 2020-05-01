package com.titut.inventory.ui.friends

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.titut.inventory.repository.FriendRepository

class FriendsViewModel(application: Application) : AndroidViewModel(application) {

    private val _text = MutableLiveData<String>().apply {
        value = "This is friends Fragment"
    }
    val text: LiveData<String> = _text

    private var repository: FriendRepository = FriendRepository(application)

    fun getFriends() = repository.getFriends()
}