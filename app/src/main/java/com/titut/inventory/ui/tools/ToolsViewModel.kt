package com.titut.inventory.ui.tools

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.titut.inventory.repository.ToolRepository

class ToolsViewModel(application: Application) : AndroidViewModel(application) {

    private val _text = MutableLiveData<String>().apply {
        value = "This is tools Fragment"
    }
    val text: LiveData<String> = _text

    private var repository: ToolRepository = ToolRepository(application)

    fun getTools() = repository.getTools()
}