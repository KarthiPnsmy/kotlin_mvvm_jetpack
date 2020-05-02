package com.titut.inventory.ui.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.titut.inventory.repository.FriendRepository
import com.titut.inventory.repository.ToolRepository

class DetailViewModel(application: Application) : AndroidViewModel(application) {

//    private var repository: FriendRepository = FriendRepository(application)
//
//    fun getFriendsWithToolsByFriend(id: Long) = repository.getFriendsWithToolsByFriend(id)

    private var repository: ToolRepository = ToolRepository(application)

    fun getToolsWithFriendOnLoan(id: Long) = repository.getToolsWithFriendOnLoan(id)

}