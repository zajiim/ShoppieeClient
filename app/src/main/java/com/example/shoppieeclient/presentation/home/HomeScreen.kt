package com.example.shoppieeclient.presentation.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.shoppieeclient.ui.theme.BackGroundColor

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onNavigateToDetails: () -> Unit,
) {
    Column  (
        modifier = modifier.fillMaxSize().verticalScroll(rememberScrollState()).background(
            BackGroundColor
        ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Home Screen")

        Button(onClick = {
            onNavigateToDetails()
        }) {
            Text("Go to Details")

        }
        Text("Home Screen")
        Spacer(Modifier.height(40.dp))
        Text("Home Screen")
        Spacer(Modifier.height(40.dp))
        Text("Home Screen")
        Text("Home Screen")
        Spacer(Modifier.height(40.dp))
        Text("Home Screen")
        Spacer(Modifier.height(40.dp))
        Text("Home Screen")
        Text("Home Screen")
        Text("Home Screen")
        Text("Home Screen")
        Spacer(Modifier.height(40.dp))
        Text("Home Screen")
        Spacer(Modifier.height(40.dp))
        Text("Home Screen")
        Text("Home Screen")
        Spacer(Modifier.height(40.dp))
        Text("Home Screen")
        Spacer(Modifier.height(40.dp))
        Text("Home Screen")
        Spacer(Modifier.height(40.dp))
        Text("Home Screen")
        Spacer(Modifier.height(40.dp))
        Text("Home Screen")
        Text("Home Screen")
        Spacer(Modifier.height(40.dp))
        Text("Home Screen")
        Spacer(Modifier.height(40.dp))
    }
}