package com.dev.equalexpert.interviewquestions

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember

@Composable
fun DerivedStateExample(count: Int) {
    // AVOID: Overkill for a simple calculation.
    val isCountEvenDerived by remember { derivedStateOf { count % 2 == 0 } }

    // PREFER: The calculation is cheap, just do it directly.
    val isCountEven = count % 2 == 0

    Text("Is count even? $isCountEven")
}