package com.example.shoppieeclient.presentation.home.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
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

private const val TAG = "HomeScreen"

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onNavigateToDetails: () -> Unit,
) {
    var query by remember {
        mutableStateOf("")
    }
    var selectedChip by remember { mutableStateOf(searchKeys.keys.first()) }
    val toggleMenu = LocalToggleMenu.current
    val isMenuOpen = LocalIsMenuOpen.current

    LazyColumn {
        item {
            CustomTopAppBar(title = "Store Location", subTitle = "New Delhi", trailingIcon = {
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
            SearchBar(modifier = Modifier.padding(top = 16.dp),
                query = query,
                onQueryChange = { query = it },
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
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(searchKeys.toList()) { (brandName, brandIcon) ->
                    CustomSuggestionChip(brand = brandName,
                        iconResId = brandIcon,
                        isExpanded = selectedChip == brandName,
                        onClick = {
                            selectedChip = brandName
                        })

                }
            }
        }
    }
}