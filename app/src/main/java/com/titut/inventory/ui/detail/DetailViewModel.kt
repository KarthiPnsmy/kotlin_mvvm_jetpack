package com.titut.inventory.ui.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.titut.inventory.repository.FriendRepository

class DetailViewModel(application: Application) : AndroidViewModel(application) {

    private var repository: FriendRepository = FriendRepository(application)

    fun getFriendsWithToolsByFriend(id: Long) = repository.getFriendsWithToolsByFriend(id)
}