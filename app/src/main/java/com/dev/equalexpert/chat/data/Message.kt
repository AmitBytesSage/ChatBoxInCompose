package com.dev.equalexpert.chat.data

import java.util.UUID

sealed class Message(
    val text: String,
    val id: String= UUID.randomUUID().toString()
) {
    data class MyMessage(val msg: String) : Message(msg)

    data class OthersMessage(val msg: String) : Message(msg)
}

data class TextMessage(val text: String, val subText: String)