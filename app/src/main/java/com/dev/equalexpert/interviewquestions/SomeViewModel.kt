package com.dev.equalexpert.interviewquestions

// In your build.gradle.kts, ensure you have this dependency:
// implementation("androidx.lifecycle:lifecycle-runtime-compose:2.8.1")

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.Flow

@Composable
fun FlowCollectionExample(viewModel: SomeViewModel) {
    // OLD WAY: Keeps collecting even when the app is in the background.
    val itemsOld by viewModel.items.collectAsState(initial = emptyList())

    // RECOMMENDED WAY: Stops collecting when the app is in the background.
    // Saves battery and resources.
    val itemsNew by viewModel.items.collectAsStateWithLifecycle(initialValue = emptyList())

    // ... UI using itemsNew ...
}

abstract class SomeViewModel : ViewModel() {
    abstract val items: Flow<List<String>>
}
/*

import kotlinx.coroutines.*
import kotlin.system.*
import kotlin.collections.*
import java.util.*
import java.time.LocalDate
fun main() = runBlocking {
    sum(4,2,3, sumLambda)
}
val sumLambda :(a : Int, b: Int) -> Int = { a, b ->
    a+b
}
fun sum(k:Int,a:Int, b:Int, lam:(Int, Int)->Int){
    println(k== lam(a,b))
}
fun getAllSortedByDateDescThenByName(): List<Person> {
        return persons.sortedWith(
            compareByDescending<Person> { it.date }
                .thenBy { it.name }
        )
    }
    // Sort by formatted date string (locale-aware)
    fun getAllSortedByFormattedDateDesc(): List<Person> {
        return persons.sortedByDescending {
            it.date.format(DateTimeFormatter.ISO_LOCAL_DATE)
        }
    }
    val minHeap = PriorityQueue<Int>()
    minHeap.addAll(listOf(5, 1, 8, 3, 2))
    println("Min-Heap (natural order):")
    while (minHeap.isNotEmpty()) {
        print("${minHeap.poll()} ")  // Output: 1 2 3 5 8
    }
    println("\n")
    // 2. Max-heap using reverse order
    val maxHeap = PriorityQueue<Int>(compareByDescending<Person> { it.date })
    maxHeap.addAll(listOf(5, 1, 8, 3, 2))
    println("Max-Heap (reverse order):")
    while (maxHeap.isNotEmpty()) {
        print("${maxHeap.poll()} ")  // Output: 8 5 3 2 1
    }
*/
