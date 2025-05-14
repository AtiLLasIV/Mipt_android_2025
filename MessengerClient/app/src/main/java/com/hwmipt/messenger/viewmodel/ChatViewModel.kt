package com.hwmipt.messenger.viewmodel

import androidx.lifecycle.*
import com.hwmipt.messenger.data.*
import com.hwmipt.messenger.data.model.ChatModel
import com.hwmipt.messenger.data.model.MessageModel
import kotlinx.coroutines.launch

class ChatViewModel(private val repository: ChatRepository) : ViewModel() {

    private val _chats = MutableLiveData<List<ChatModel>>()
    val chats: LiveData<List<ChatModel>> get() = _chats

    private val _messages = MutableLiveData<List<MessageModel>>()
    val messages: LiveData<List<MessageModel>> get() = _messages

    private val _selectedChatId = MutableLiveData<Int?>()
    val selectedChatId: LiveData<Int?> get() = _selectedChatId


    fun createChat(name: String) {
        viewModelScope.launch {
            val result = repository.createChat(name)
            _chats.value = result ?: emptyList()
        }
    }

    fun selectChat(id: Int) {
        _selectedChatId.value = id
        loadMessages(id)
    }

    fun sendMessage(text: String) {
        val chatId = _selectedChatId.value ?: return
        viewModelScope.launch {
            val result = repository.sendMessage(chatId, text)
            _messages.value = result ?: emptyList()
        }
    }

    fun loadChats() {
        viewModelScope.launch {
            val result = repository.getChats()
            _chats.value = result ?: emptyList()
        }
    }

    fun loadMessages(chatId: Int) {
        viewModelScope.launch {
            val result = repository.getMessages(chatId)
            _messages.value = result ?: emptyList()
        }
    }
}
