package com.example.shoppieeclient.presentation.home.bottom_nav_bar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.shoppieeclient.presentation.navigation.Destination
import com.example.shoppieeclient.ui.theme.Primary

@Composable
fun CustomBottomNavBar(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    items: List<BottomBarScreen>
) {

    Box {
        IconButton(
            modifier = Modifier
                .padding(top = 20.dp)
                .size(64.dp)
                .clip(CircleShape)
                .background(Primary)
                .padding(16.dp)
                .align(Alignment.TopCenter),
            onClick = {
                navController.navigate(Destination.Cart) {
                    popUpTo<Destination>(Destination.Home){
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }) {
            Icon(
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = "Cart"
            )
        }


        Box(
            modifier = modifier
                .safeDrawingPadding()
                .fillMaxWidth()
                .height(106.dp)
                .clip(CustomNavBarShape())
                .background(Color.White)
        ) {


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {

                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                items.forEach { screen ->
                    val isSelected =
                        currentDestination?.hierarchy?.any { item -> item == screen.destination } == true
                    NavigationBarItem(
                        selected = isSelected,
                        icon = {
                            (screen.selectedIcon ?: screen.unSelectedIcon)?.let {
                                Icon(it, contentDescription = screen.title)
                            }
                        },
                        onClick = {
                            navController.navigate(screen.destination) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }

            }
        }
    }


}

@Preview
@Composable
private fun CustomnavBarPreview() {
    CustomBottomNavBar(
        items = listRoutes,
        navController = rememberNavController()
    )
}