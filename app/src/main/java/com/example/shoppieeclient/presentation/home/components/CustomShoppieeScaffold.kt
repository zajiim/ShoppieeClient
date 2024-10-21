package com.example.shoppieeclient.presentation.home.components

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.shoppieeclient.ui.LocalIsMenuOpen
import com.example.shoppieeclient.ui.LocalToggleMenu

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "RestrictedApi")
@Composable
fun CustomShoppieeScaffold(
    navController: NavHostController,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val displayMetrics = context.resources.displayMetrics
    val width = with(LocalDensity.current) { displayMetrics.widthPixels.toDp() }
    val height = with(LocalDensity.current) { displayMetrics.heightPixels.toDp() }
    var isMenuOpen by remember { mutableStateOf(false) }
    val animatedProgress by animateFloatAsState(
        targetValue = if (isMenuOpen) 1f else 0f,
        animationSpec = tween(durationMillis = 500),
        label = "collapse animation"
    )
    CompositionLocalProvider(
        LocalIsMenuOpen provides isMenuOpen,
        LocalToggleMenu provides { isMenuOpen = !isMenuOpen }
    ) {

        Box(modifier = Modifier.fillMaxSize()) {
            SideMenu(
                alpha = animatedProgress, onNavigate = {
                    isMenuOpen = false
                    navController.navigate(it)
                }, width = width,
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .scale(1f - 0.26f * animatedProgress)
                    .offset(
                        x = (width * 0.6f * animatedProgress),
                        y = (height * 0.06f * animatedProgress)
                    )
                    .rotate(-6.62f * animatedProgress)
                    .clip(shape = RoundedCornerShape(25.dp * animatedProgress))
                    .clickable {
                        if (isMenuOpen) {
                            isMenuOpen = false
                        }
                    }
            ) {
                MainContent(
                    navController = navController,
                    content = content,
//                isMenuOpen = isMenuOpen,
//                onToggleMenu = { isMenuOpen = !isMenuOpen }
                )
            }
        }
    }
}




@Preview
@Composable
private fun PreviewCustomScaffold() {
    CustomShoppieeScaffold(navController = rememberNavController(), content = {})
}