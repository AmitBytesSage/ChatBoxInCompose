package com.dev.equalexpert

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.equalexpert.data.Message
import com.dev.equalexpert.data.Message.MyMessage
import com.dev.equalexpert.data.Message.OthersMessage
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ChatViewModel(initialMessages: List<Message>) : ViewModel() {
    private val _messages = MutableStateFlow(initialMessages)
    val messages: StateFlow<List<Message>> = _messages

    fun sendMessage(messageText: String) {
        val newMessage = MyMessage(messageText)
        _messages.value += newMessage
        simulateReply()
    }

    private fun simulateReply() {
        viewModelScope.launch {
            delay(5000)
            val replyMessage = OthersMessage("This is a reply")
            _messages.value += replyMessage
        }
    }
}