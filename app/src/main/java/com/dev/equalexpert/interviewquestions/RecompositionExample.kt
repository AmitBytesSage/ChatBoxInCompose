package com.dev.equalexpert.interviewquestions

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel

// region 1. What actually happens during recomposition?
@Composable
fun RecompositionExample() {
    var count by remember { mutableStateOf(0) }
    Log.d("Recomposition", "RecompositionExample was composed")

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = { count++ }) {
            // When the button is clicked, `count` changes.
            // This triggers recomposition ONLY for composables reading `count`.
            Text("Click me")
        }
        // This Text composable reads `count`, so it will be re-run.
        Text("Count: $count")
        Log.d("Recomposition", "Count Text was composed")

        // This Text does NOT read `count`, so it is SKIPPED during recomposition.
        Text("This text is static")
        Log.d("Recomposition", "Static Text was composed")
    }
}
// endregion

// region 2 & 6. Why does Compose recompose? (Stable vs Unstable types)

// UNSTABLE: Standard mutable list causes recomposition even if its content is the same
class UnstableViewModel : ViewModel() {
    // Using a standard List is unstable.
    var names: List<String> by mutableStateOf(listOf("John", "Doe"))
}

// STABLE: Data class and primitive types are stable.
data class User(val id: Int, val name: String) // Data classes are stable

class StableViewModel : ViewModel() {
    // Using a data class is stable.
    var user: User by mutableStateOf(User(1, "John"))
}

// Unstable class
class Person(val name: String)

@Composable
fun UnstableGreeting(person: Person) {
    // This will recompose every time its parent recomposes, because Person is unstable.
    Text("Hello, ${person.name}")
}

// Stable data class
data class DataPerson(val name: String)

@Composable
fun StableGreeting(person: DataPerson) {
    // This will be SKIPPED if the person object is the same as the last composition.
    Text("Hello, ${person.name}")
}
// endregion

// region 3. Difference between remember and rememberSaveable
@Composable
fun StatePersistenceExample() {
    // This counter will reset on screen rotation.
    var simpleCount by remember { mutableStateOf(0) }

    // This counter will survive screen rotation.
    var saveableCount by rememberSaveable { mutableStateOf(0) }

    Column {
        Text("`remember` count: $simpleCount")
        Button(onClick = { simpleCount++ }) { Text("Increment `remember`") }

        Spacer(Modifier.height(16.dp))

        Text("`rememberSaveable` count: $saveableCount")
        Button(onClick = { saveableCount++ }) { Text("Increment `rememberSaveable`") }
    }
}
// endregion

// region 4. When does LaunchedEffect not re-run?
@Composable
fun LaunchedEffectExample(userId: Int) {
    // This runs ONCE when the composable is first displayed.
    LaunchedEffect(Unit) {
        Log.d("Effect", "Composable entered the composition.")
    }

    // This re-runs EVERY TIME `userId` changes.
    // It will NOT re-run if the composable recomposes but `userId` is the same.
    LaunchedEffect(userId) {
        Log.d("Effect", "Fetching data for user: $userId")
        // fetchDataForUser(userId)
    }
}
// endregion

// region 5. Why is key so important in LazyColumn?
@Composable
fun UserList(users: List<User>) {
    LazyColumn {
        // By providing a stable `key`, Compose can uniquely identify each item.
        // This is crucial for performance and state preservation within each item.
        items(
            items = users,
            key = { user -> user.id } // The magic is here!
        ) { user ->
            Text("User: ${user.name}")
        }
    }
}
// endregion

