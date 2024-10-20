package com.example.shoppieeclient.presentation.home.components

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.shoppieeclient.R
import com.example.shoppieeclient.presentation.home.bottom_nav_bar.CustomBottomNavBar
import com.example.shoppieeclient.presentation.home.bottom_nav_bar.listRoutes
import com.example.shoppieeclient.presentation.navigation.Destination
import com.example.shoppieeclient.ui.LocalIsMenuOpen
import com.example.shoppieeclient.ui.LocalToggleMenu
import com.example.shoppieeclient.ui.theme.ScaffoldBgColor
import com.example.shoppieeclient.ui.theme.ScaffoldTextColorFaded

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "RestrictedApi")
@Composable
fun CustomShoppieeScaffold(
    navController: NavHostController,
    content: @Composable (PaddingValues) -> Unit
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
        LocalToggleMenu provides {isMenuOpen = !isMenuOpen}
    ) {

    Box(modifier = Modifier.fillMaxSize()) {
        SideMenu(
            alpha = animatedProgress, onNavigate = {
                isMenuOpen = false
                navController.navigate(it)
            }, width = width
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .scale(1f - 0.26f * animatedProgress)
                .offset(
                    x = (width * 0.6f * animatedProgress), y = (height * 0.06f * animatedProgress)
                )
                .rotate(-6.62f * animatedProgress)
                .clip(shape = RoundedCornerShape(25.dp * animatedProgress))
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


@SuppressLint("RestrictedApi")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent(
    navController: NavHostController,
    content: @Composable (PaddingValues) -> Unit,
//    isMenuOpen: Boolean,
//    onToggleMenu: () -> Unit
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
//    val showTopAppBar = currentDestination?.hasRoute(Destination.Home::class)
    val mainDestinations = listOf(
        Destination.Home,
        Destination.Favorites,
        Destination.Cart,
        Destination.Notifications,
        Destination.Profile
    )
    Scaffold(
//        topBar = {
//            if (showTopAppBar == true) {
//                IconButton(onClick = onMenuClick) {
//                    Icon(
//                        painter = painterResource(id = R.drawable.ic_menu_home),
//                        contentDescription = "Menu"
//                    )
//                }
//            }
//        },
        bottomBar = {
            if (mainDestinations.any { destination ->
                    currentDestination?.hasRoute(destination::class) == true
                }) {
                CustomBottomNavBar(
                    navController = navController, items = listRoutes
                )
            }
        }) { innerPadding ->

            content(innerPadding)

    }
}


@Composable
fun SideMenu(
    alpha: Float, onNavigate: (Destination) -> Unit, width: Dp
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ScaffoldBgColor)
            .padding(horizontal = 20.dp)
            .graphicsLayer(alpha = alpha)
    ) {
        Image(
            modifier = Modifier
                .padding(top = 54.dp)
                .size(64.dp)
                .clip(CircleShape),
            painter = painterResource(R.drawable.user_image),
            contentDescription = null
        )

        Spacer(Modifier.height(24.dp))
        Text(
            text = "Hey, 👋",
            color = ScaffoldTextColorFaded,
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(Modifier.height(6.dp))
        Text(
            text = "Alisson Becker",
            color = Color.White,
            style = MaterialTheme.typography.headlineLarge
        )
        Spacer(Modifier.height(50.dp))

        listRoutes.forEach { item ->
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable {
                onNavigate(item.destination)
            }) {
                IconButton(onClick = { }) {
                    when {
                        item.title == "Cart" -> Icon(
                            painter = painterResource(id = R.drawable.ic_cart),
                            contentDescription = item.title,
                            tint = ScaffoldTextColorFaded
                        )

                        item.icon != null -> Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = item.title,
                            tint = ScaffoldTextColorFaded
                        )
                    }
                }
                Text(text = item.title, color = Color.White)
            }
        }

        Spacer(Modifier.height(50.dp))
        Spacer(
            Modifier
                .height(2.dp)
                .background(ScaffoldTextColorFaded)
                .width(width * 0.25f)
        )
        Spacer(Modifier.height(50.dp))

        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.clickable { }) {
            Icon(
                painter = painterResource(R.drawable.ic_sign_out),
                contentDescription = null,
                tint = ScaffoldTextColorFaded
            )
            Text("Sign out", color = Color.White)
        }
    }
}


@Preview
@Composable
private fun PreviewCustomScaffold() {
    CustomShoppieeScaffold(navController = rememberNavController(), content = {})
}