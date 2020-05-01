package com.titut.inventory.ui.tools

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.titut.inventory.db.entity.Friend
import com.titut.inventory.db.entity.Tool
import com.titut.inventory.repository.FriendRepository
import com.titut.inventory.repository.ToolRepository

class ToolsViewModel(application: Application) : AndroidViewModel(application) {

//    private val _tools = MutableLiveData<List<Tool>>()
//    val tools: LiveData<List<Tool>> = _tools

    private var toolsRepository: ToolRepository = ToolRepository(application)

    fun getTools() = toolsRepository.getTools()

//    val getToolsList = toolsRepository.getTools()
//
//    private val _tools = MutableLiveData<List<Tool>>().apply {
//        value = toolsRepository.getTools()
//    }
//    val tools: LiveData<List<Tool>> = _tools


//    fun getTools(){
//        mutableDepartureListData.value = toolsRepository.getTools().value
//    }

}