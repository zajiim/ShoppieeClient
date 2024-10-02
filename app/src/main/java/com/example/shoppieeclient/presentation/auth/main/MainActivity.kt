package com.example.shoppieeclient.presentation.auth.main

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeGesturesPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.shoppieeclient.domain.common.model.NetworkStatus
import com.example.shoppieeclient.domain.common.repository.NetworkConnectivityObserver
import com.example.shoppieeclient.presentation.common.components.NetworkStatusBar
import com.example.shoppieeclient.presentation.navigation.ShoppieNavGraph
import com.example.shoppieeclient.ui.theme.ShoppieeClientTheme
import kotlinx.coroutines.delay
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val TAG = "MainActivity"

class MainActivity : ComponentActivity() {
    private val connectivityObserver: NetworkConnectivityObserver by inject()
    private val mainActivityViewModel: MainActivityViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().setKeepOnScreenCondition {
            Log.e(TAG, "onCreate: value ===> ${mainActivityViewModel.splashCondition.value}")
            mainActivityViewModel.splashCondition.value
        }
        enableEdgeToEdge()
        setContent {
//            val status by connectivityObserver.networkStatus.collectAsState()
//            var showMessageBar by rememberSaveable { mutableStateOf(false) }
//            var message by rememberSaveable { mutableStateOf("") }
//            var backgroundColor by remember { mutableStateOf(Color.Red) }

//            LaunchedEffect(key1 = status) {
//                when (status) {
//                    NetworkStatus.Connected -> {
//                        message = "Connected To Internet"
//                        backgroundColor = Color.Green
//                        delay(timeMillis = 2000)
//                        showMessageBar = false
//                    }
//
//                    NetworkStatus.Disconnected -> {
//                        showMessageBar = true
//                        message = "No Internet Connection"
//                        backgroundColor = Color.Red
//                    }
//                }
//            }


            ShoppieeClientTheme {
//                val snackBarHostState = remember { SnackbarHostState() }

                Scaffold(
//                    snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
                    modifier = Modifier.fillMaxSize(),
//                    bottomBar = {
//                        NetworkStatusBar(
//                            modifier = Modifier.safeGesturesPadding(),
//                            showMessageBar = showMessageBar,
//                            message = message,
//                            backgroundColor = backgroundColor
//                        )
//                    }
                ) { innerPadding ->
                    val navController = rememberNavController()
                    ShoppieNavGraph(
                        navController = navController,
                        connectivityObserver = connectivityObserver
                        )
                }
            }
        }
    }
}
