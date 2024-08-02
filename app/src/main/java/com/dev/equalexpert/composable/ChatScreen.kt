package com.dev.equalexpert.composable


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dev.equalexpert.ChatViewModel
import com.dev.equalexpert.ChatViewModelFactory
import com.dev.equalexpert.data.Message.MyMessage
import com.dev.equalexpert.data.Message.OthersMessage


@Composable
fun ChatScreen(
    modifier: Modifier,
    chatViewModel: ChatViewModel = viewModel(factory = ChatViewModelFactory(emptyList()))
) {
    Column(modifier.fillMaxSize()) {
        MessageList(
            modifier = Modifier.weight(1f),
            messages = chatViewModel.messages
        )
        MessageInput(
            modifier = Modifier
                .fillMaxWidth(),
            onMessageSent = { message ->
                chatViewModel.sendMessage(message)
            }
        )
    }
}

@Preview
@Composable
fun PreviewChatScreen() {
    val items = listOf(
        MyMessage("Hi"),
        OthersMessage("Hello !!")
    )
    ChatScreen(
        modifier = Modifier,
        chatViewModel = viewModel(factory = ChatViewModelFactory(items))
    )
}