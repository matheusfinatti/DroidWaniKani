package com.mfinatti.wanikanisimple.home.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen(
    name: String,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.fillMaxSize()) {
        Text(text = "Welcome, $name", modifier = Modifier.align(Alignment.Center))
    }
}
