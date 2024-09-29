package com.example.shoppieeclient.presentation.auth.main

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.shoppieeclient.presentation.navigation.ShoppieNavGraph
import com.example.shoppieeclient.ui.theme.ShoppieeClientTheme
import org.koin.androidx.viewmodel.ext.android.getViewModel

private const val TAG = "MainActivity"
class MainActivity : ComponentActivity() {
    private val mainActivityViewModel by viewModels<MainActivityViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().setKeepOnScreenCondition {
            Log.e(TAG, "onCreate: value ===> ${mainActivityViewModel.splashCondition.value}" )
            mainActivityViewModel.splashCondition.value
        }
        enableEdgeToEdge()
        setContent {
            ShoppieeClientTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    ShoppieNavGraph(navController = navController)
                }
            }
        }
    }
}
