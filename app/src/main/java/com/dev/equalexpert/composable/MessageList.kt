package com.dev.equalexpert.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.dev.equalexpert.data.Message
import kotlinx.coroutines.flow.StateFlow

@Composable
fun MessageList(modifier: Modifier, messages: StateFlow<List<Message>>) {
    Box(modifier = modifier) {
        val messageState = messages.collectAsState()
        val chatListState = rememberLazyListState()

        LaunchedEffect(messageState.value){
            chatListState.animateScrollToItem(chatListState.layoutInfo.totalItemsCount)
        }
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            state = chatListState
        ) {
            items(items = messageState.value) { message ->
                MessageItem(message)
            }
        }
    }
}
