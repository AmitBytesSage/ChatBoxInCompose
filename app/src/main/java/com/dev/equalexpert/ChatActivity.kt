package com.dev.equalexpert

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dev.equalexpert.composable.ChatScreen
import com.dev.equalexpert.home.HomeScreen
import com.dev.equalexpert.ktor.NewsArticles
import com.dev.equalexpert.ui.theme.EqualExpertTheme

class ChatActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




        setContent {
            EqualExpertTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            colors = TopAppBarDefaults.mediumTopAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                titleContentColor = MaterialTheme.colorScheme.primary,
                            ),
                            title = {
                                Text("Chat Window")
                            }
                        )
                    }
                ) {
                    MyApp(modifier = Modifier.padding(it))
                }
            }
        }
    }

    @Composable
    fun MyApp(
        modifier: Modifier = Modifier,
        navController: NavHostController = rememberNavController()
    ) {
        NavHost(navController = navController, startDestination = "Home", modifier = modifier) {
            composable("Home") {
                HomeScreen(
                    onNavigateToFriends = {
                        navController.navigate("Chat")
                    }, onNavigateToNews = {
                        navController.navigate("News")
                    }
                )
            }
            composable("Chat") { ChatScreen(modifier = Modifier.padding(8.dp)) }
            composable("News") { NewsArticles() }
        }

    }
}

