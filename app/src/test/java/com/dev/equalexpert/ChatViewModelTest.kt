package com.dev.equalexpert

import com.dev.equalexpert.data.Message.MyMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ChatViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }


    @Test
    fun `send message adds message to list`() = runTest {

        val initialMessages = listOf(MyMessage("Hello!"))
        val viewModel = ChatViewModel(initialMessages)

        // When a new message is sent
        viewModel.sendMessage("Hi! How are you?")

        // Then the message list contains the new message
        val messages = viewModel.messages.first()
        assertEquals(2, messages.size)
        assertEquals("Hi! How are you?", messages[1].text)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `simulate reply adds reply message to list`() = runTest {

        val initialMessages = listOf(MyMessage("Hello!"))
        val viewModel = ChatViewModel(initialMessages)

        // When a new message is sent
        viewModel.sendMessage("Hi! How are you?")

        // Wait for the simulated reply delay
        advanceTimeBy(6000)

        // Then the message list contains the reply message
        val messages = viewModel.messages.first()
        assertEquals(3, messages.size)
        assertEquals("This is a reply", messages[2].text)
    }
}