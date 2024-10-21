package com.example.shoppieeclient.presentation.home.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.shoppieeclient.R
import com.example.shoppieeclient.presentation.home.home.components.CustomSuggestionChip
import com.example.shoppieeclient.presentation.home.home.components.CustomTopAppBar
import com.example.shoppieeclient.ui.LocalIsMenuOpen
import com.example.shoppieeclient.ui.LocalToggleMenu
import com.example.shoppieeclient.utils.searchKeys
import org.koin.androidx.compose.koinViewModel
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue


private const val TAG = "HomeScreen"

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = koinViewModel(),
    modifier: Modifier = Modifier,
    onNavigateToDetails: () -> Unit,
) {
    val toggleMenu = LocalToggleMenu.current
    val isMenuOpen = LocalIsMenuOpen.current
    val state = homeViewModel.uiState

    LazyColumn(
        modifier = modifier.fillMaxSize().padding(horizontal = 20.dp),
        userScrollEnabled = !isMenuOpen
    ) {
        item {
            CustomTopAppBar(title = "Store Location", subTitle = state.storeLocation, trailingIcon = {
                IconButton(onClick = toggleMenu) {
                    AsyncImage(R.drawable.ic_menu_home, contentDescription = null)
                }
            }, leadingIcon = {
                IconButton(onClick = {

                }) {
                    Icon(
                        painter = painterResource(R.drawable.ic_cart), contentDescription = null
                    )
                }
            })
        }
        item {
            SearchBar(
                modifier = Modifier.fillMaxWidth(),
                query = state.query,
                onQueryChange = { homeViewModel.onEvent(HomeEvents.OnQueryChange(it)) },
                placeholder = { Text(text = "Looking for shoes?") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search, contentDescription = "search"
                    )
                },
                onSearch = {},
                active = false,
                onActiveChange = {},
                content = {})
        }
        item {
            LazyRow(
                contentPadding = PaddingValues(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                userScrollEnabled = !isMenuOpen
            ) {
                items(state.brandsList) { (brandName, brandIcon) ->
                    CustomSuggestionChip(
                        brand = brandName,
                        iconResId = brandIcon,
                        isExpanded = state.selectedChip == brandName,
                        onClick = {
                            if (!isMenuOpen) {
                                homeViewModel.onEvent(HomeEvents.OnChipSelected(brandName))
                            }
                        })

                }
            }
        }
    }
}