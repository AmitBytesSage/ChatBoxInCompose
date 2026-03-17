package com.dev.equalexpert.composable

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import com.dev.equalexpert.chat.composable.DeleteConfirmationDialog
import com.dev.equalexpert.chat.data.Message
import kotlinx.coroutines.flow.StateFlow

@Composable
fun MessageList(
    modifier: Modifier,
    messages: StateFlow<List<Message>>,
    onDeleteMessage: (Message) -> Unit // Add a callback to delete a message
) {
    Box(modifier = modifier) {
        val messageState by messages.collectAsState()
        val chatListState = rememberLazyListState()

        // State to hold the message targeted for deletion
        var messageToDelete by remember { mutableStateOf<Message?>(null) }

        // Show the dialog when a message is selected for deletion
        if (messageToDelete != null) {
            DeleteConfirmationDialog(
                onConfirm = {
                    messageToDelete?.let { onDeleteMessage(it) }
                },
                onDismiss = { messageToDelete = null }
            )
        }

        // Scroll to the bottom when a new message arrives
        LaunchedEffect(messageState.size) {
            if (messageState.isNotEmpty()) {
                chatListState.animateScrollToItem(messageState.size - 1)
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            state = chatListState
        ) {
            items(items = messageState, key = { it.id }) { message ->
                MessageItem(
                    message = message,
                    // Set the message to be deleted on long press
                    onLongPress = { messageToDelete = message }
                )
            }
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MessageItem(
    message: Message,
    onLongPress: (Message) -> Unit
) {
    // We assume MessageItem is a Box or other layout
    Box(
        modifier = Modifier.pointerInput(message) {
            detectTapGestures(
                onLongPress = {
                    onLongPress(message)
                }
            )
        }
    ) {
        // Your existing MessageItem content goes here.
        // For example:
//        Text(text = "${message.author}: ${message.body}")
    }
}