package com.dev.equalexpert.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    onNavigateToFriends: () -> Unit? = {},
    onNavigateToNews: () -> Unit = {},
    onNavigateToRoom: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .background(Color.LightGray)
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(text = "List of functionality")
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                onNavigateToFriends()
            },
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = "Chat screen")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                onNavigateToNews()
            },
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = "News Article")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                onNavigateToRoom()
            },
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = "Room DB")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                onNavigateToRoom()
            },
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = "Work Manager")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                onNavigateToRoom()
            },
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = "Custom Component Hilt")
        }
    }
}

@Preview
@Composable
fun PreviewChatScreen() {
    HomeScreen()
}