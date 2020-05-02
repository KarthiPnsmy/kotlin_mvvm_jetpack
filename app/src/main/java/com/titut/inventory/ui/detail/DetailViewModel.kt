package com.titut.inventory.ui.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.titut.inventory.repository.ToolRepository

class DetailViewModel(application: Application) : AndroidViewModel(application) {
    private var repository: ToolRepository = ToolRepository(application)

    fun getToolsWithFriendOnLoan(id: Long) = repository.getToolsWithFriendOnLoan(id)
}