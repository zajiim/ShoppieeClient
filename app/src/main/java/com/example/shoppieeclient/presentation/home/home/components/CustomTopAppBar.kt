package com.example.shoppieeclient.presentation.home.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String,
    trailingIcon: @Composable () -> Unit = {},
    leadingIcon: @Composable (RowScope.() -> Unit) = {}
) {

    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = title, style = TextStyle(
                        fontSize = MaterialTheme.typography.titleLarge.fontSize,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                    )
                )

                Text(
                    text = subTitle, style = TextStyle(
                        fontSize = MaterialTheme.typography.titleSmall.fontSize,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontFamily = MaterialTheme.typography.titleSmall.fontFamily,
                    )
                )
            }
        }, actions = leadingIcon, colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            titleContentColor = MaterialTheme.colorScheme.onBackground,
            actionIconContentColor = MaterialTheme.colorScheme.onBackground,
            navigationIconContentColor = MaterialTheme.colorScheme.onBackground
        ), navigationIcon = trailingIcon
    )
}