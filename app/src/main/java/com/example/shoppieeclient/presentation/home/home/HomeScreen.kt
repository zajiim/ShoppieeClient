package com.example.shoppieeclient.presentation.home.home

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.shoppieeclient.R
import com.example.shoppieeclient.presentation.home.home.components.CustomNoInternet
import com.example.shoppieeclient.presentation.home.home.components.CustomSuggestionChip
import com.example.shoppieeclient.presentation.home.home.components.CustomTopAppBar
import com.example.shoppieeclient.presentation.home.home.components.NewArrivalsItem
import com.example.shoppieeclient.presentation.home.home.components.ShoppieeShoesItem
import com.example.shoppieeclient.ui.LocalIsMenuOpen
import com.example.shoppieeclient.ui.LocalToggleMenu
import org.koin.androidx.compose.koinViewModel


private const val TAG = "HomeScreen"

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = koinViewModel(),
    modifier: Modifier = Modifier,
    onNavigateToDetails: (String) -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    val toggleMenu = LocalToggleMenu.current
    val isMenuOpen = LocalIsMenuOpen.current
    val state = homeViewModel.uiState

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        userScrollEnabled = !isMenuOpen,
    ) {
        item {
            CustomTopAppBar(modifier = modifier.padding(horizontal = 16.dp),
                title = "Store Location",
                subTitle = state.storeLocation,
                trailingIcon = {
                    IconButton(onClick = toggleMenu) {
                        AsyncImage(R.drawable.ic_menu_home, contentDescription = null)
                    }
                },
                leadingIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(R.drawable.ic_cart), contentDescription = null
                        )
                    }
                })
        }
        item {
            if (state.homeError != null) {
                CustomNoInternet(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 20.dp),
                    message = state.homeError,
                )
            } else {
                Column {
                    SearchBar(modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
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

                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 10.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        userScrollEnabled = !isMenuOpen
                    ) {
                        items(state.brandsList) { (brandName, brandIcon) ->
                            CustomSuggestionChip(brand = brandName,
                                iconResId = brandIcon,
                                isExpanded = state.selectedChip == brandName,
                                onClick = {
                                    if (!isMenuOpen) {
                                        homeViewModel.onEvent(HomeEvents.OnChipSelected(brandName))
                                    }
                                })
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    state.homeItemsList?.popularItemsModel?.let {
                        ShoppieeShoesItem(leadingTitle = "Popular items",
                            trailingTitle = "See more",
                            isLoading = state.isLoading,
                            shoeItems = it,
                            userScrollable = !isMenuOpen,
                            onItemClick = { itemId -> onNavigateToDetails(itemId) },
                            sharedTransitionScope = sharedTransitionScope,
                            animatedVisibilityScope = animatedVisibilityScope
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    state.homeItemsList?.newArrivalItemsModel?.let {
                        NewArrivalsItem(
                            modifier = Modifier.fillMaxWidth(),
                            leadingTitle = "New Arrivals",
                            trailingTitle = "See more",
                            isLoading = state.isLoading,
                            shoes = it,
                            userScrollable = !isMenuOpen
                        )
                    }

                    Spacer(modifier = Modifier.height(56.dp))

//                    state.homeItemsList?.popularItemsModel?.let {
//                        ShoppieeShoesItem(leadingTitle = "Popular items",
//                            trailingTitle = "See more",
//                            isLoading = state.isLoading,
//                            shoeItems = it,
//                            userScrollable = !isMenuOpen,
//                            onItemClick = { itemId -> onNavigateToDetails(itemId) })
//                    }
                }
            }
        }
    }
}