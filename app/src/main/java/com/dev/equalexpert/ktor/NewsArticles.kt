package com.dev.equalexpert.ktor

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dev.equalexpert.constant.Const.FAKE_API_URL
import com.dev.equalexpert.ktor.data.Article
import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.*
import kotlinx.serialization.json.Json
import kotlin.collections.List
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.Serializable
import kotlin.math.log


@Composable
fun NewsArticles() {
    val articles = remember { mutableStateListOf<Article>() }
// Ktor Client setup
    val client = remember { createKtorClient() }
    var isError by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(Unit) {
        try {
            val response = client.get(FAKE_API_URL) {
                headers {
                    set("Accept", "application/json")
                }
            }
            val apiResponse = response.body<List<Article>>()
            articles.clear()
            articles.addAll(apiResponse)
        } catch (e: Exception) {
            // Handle error (e.g., show snackbar)
            Log.d("News", e.printStackTrace().toString())
            isError = true
        }
    }

    if (isError) {
        Text(text = "Error fetching articles")
    }
    LazyColumn() {
        items(
            count = articles.size,
            key = { articles[it].id },
            contentType = { 0 },
            itemContent = {
                ArticleItem(article = articles[it])
            }
        )
    }
}

@Composable
fun ArticleItem(article: Article) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = article.title,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = article.description,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

private fun createKtorClient(): HttpClient {
    return HttpClient(OkHttp) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true // Highly recommended for stability
            })
        }

        // Optional: Add a timeout so your app doesn't hang
        install(HttpTimeout) {
            requestTimeoutMillis = 15000
            connectTimeoutMillis = 15000
        }
    }
}

@Serializable
data class NewsArticlesList(val list: List<Article>)