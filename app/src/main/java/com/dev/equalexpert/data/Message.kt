package com.dev.equalexpert.data

sealed class Message(val text: String) {
    data class MyMessage(val msg: String) : Message(msg)

    data class OthersMessage(val msg: String) : Message(msg)
}