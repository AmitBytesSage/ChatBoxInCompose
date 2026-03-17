package com.dev.equalexpert

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.equalexpert.chat.data.Message
import com.dev.equalexpert.chat.data.Message.MyMessage
import com.dev.equalexpert.chat.data.Message.OthersMessage
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class ChatViewModel(initialMessages: List<Message>) : ViewModel() {

    private val _messages = MutableStateFlow<List<Message>>(initialMessages)
    val messages: StateFlow<List<Message>> = _messages

    fun deleteMessage(message: Message) {
        // Create a new list excluding the message to be deleted
        _messages.value = _messages.value.filterNot { it.id == message.id }
    }

    fun sendMessage(messageText: String) {
        val newMessage = MyMessage(messageText)
        _messages.value += newMessage
        simulateReply()

        val obj: Flow<Int> = flow {
            emit(1)
        }

        val obj3:Flow<Int> = flow {
            emit(2)
        }
    }

    private fun simulateReply() {
        viewModelScope.launch {
            delay(5000)
            val replyMessage = OthersMessage("This is a reply")
            _messages.value += replyMessage
        }
    }
}