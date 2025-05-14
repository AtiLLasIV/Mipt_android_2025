package com.hwmipt.messenger.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hwmipt.messenger.data.ChatRepository

class ChatViewModelFactory(
    private val repository: ChatRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ChatViewModel(repository) as T
    }
}
