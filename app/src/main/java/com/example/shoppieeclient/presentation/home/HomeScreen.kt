package com.example.shoppieeclient.presentation.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.shoppieeclient.presentation.home.bottom_nav_bar.ShoppieeBottomNavBar
import com.example.shoppieeclient.presentation.navigation.Destination
import com.example.shoppieeclient.ui.theme.BackGroundColor

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
) {
    Column  (
        modifier = Modifier.fillMaxSize().background(BackGroundColor)
    ) {
        LazyColumn(


        ) {
            item {

                Text("home")


            }
        }
    }
}