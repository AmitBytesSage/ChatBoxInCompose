package com.dev.equalexpert.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.dev.equalexpert.data.Message
import com.dev.equalexpert.data.Message.MyMessage
import com.dev.equalexpert.data.Message.OthersMessage

@Composable
fun MessageItem(message: Message) {
    when (message) {
        is MyMessage -> {
            MyMessageItem(message)
        }

        is OthersMessage -> {
            OtherMessageItem(message)
        }
    }
}

@Composable
fun MyMessageItem(message: MyMessage) {
    Row(
        horizontalArrangement = Arrangement.End,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            text = message.text,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White,
            modifier = Modifier
                .background(
                    Color.Blue,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(8.dp)
        )
    }
}

@Composable
fun OtherMessageItem(message: OthersMessage) {
    Row(
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            text = message.text,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black,
            modifier = Modifier
                .background(
                    Color.Gray,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(8.dp)
        )
    }
}