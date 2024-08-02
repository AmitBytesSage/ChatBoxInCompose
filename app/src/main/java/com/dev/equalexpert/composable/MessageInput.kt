package com.dev.equalexpert.composable

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@Composable
fun MessageInput(modifier: Modifier, onMessageSent: (String) -> Unit) {
    var message by remember { mutableStateOf("") }

    fun sendMessage() {
        onMessageSent(message)
        message = ""
    }

    Row(
        modifier = modifier
            .padding(8.dp),
        verticalAlignment = androidx.compose.ui.Alignment.Bottom
    ) {
        TextField(
            value = message,
            onValueChange = { message = it },
            modifier = Modifier.weight(1f),
            placeholder = { Text("Type a message") },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Send),
            keyboardActions = KeyboardActions { sendMessage() }
        )
        Spacer(modifier = Modifier.width(16.dp))
        Button(
            onClick = {
                if (message.isNotEmpty()) {
                    sendMessage()
                }
            }
        ) {
            Text("Send")
        }
    }
}