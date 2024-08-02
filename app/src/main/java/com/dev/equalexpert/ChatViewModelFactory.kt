package com.dev.equalexpert

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dev.equalexpert.data.Message

class ChatViewModelFactory(private val initialMessages: List<Message>) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChatViewModel::class.java)) {
            return ChatViewModel(initialMessages) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}