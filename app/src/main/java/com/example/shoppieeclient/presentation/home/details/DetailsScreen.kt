package com.example.shoppieeclient.presentation.home.details

import android.R.attr.maxLines
import android.util.Log
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shoppieeclient.presentation.home.details.components.CustomSizeSection
import com.example.shoppieeclient.presentation.home.details.components.ProductImage
import com.example.shoppieeclient.presentation.home.details.components.ThumbnailImage
import com.example.shoppieeclient.ui.theme.BackGroundColor
import com.example.shoppieeclient.ui.theme.PrimaryBlue
import com.example.shoppieeclient.ui.theme.SubTitleColor
import kotlinx.coroutines.launch

private const val TAG = "DetailsScreen"

@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: DetailsViewModel
) {

    val uiState = viewModel.uiState
    val product = uiState.details
    val scope = rememberCoroutineScope()
    Log.d(TAG, "DetailsScreen: product name == ${product?.name}")


    val pagerState = rememberPagerState(pageCount = { product?.images?.size ?: 1 })

    LaunchedEffect(pagerState.currentPage) {
        viewModel.onEvent(DetailsEvent.SelectImage(pagerState.currentPage))
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(BackGroundColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            product?.images?.let { images ->
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                ) { page ->
                    ProductImage(
                        modifier = Modifier,
                        imageUrl = images[page],
                    )
                }
            }

            product?.category?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.titleSmall.copy(color = PrimaryBlue),
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }

            product?.name?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }

            product?.price?.let {
                Text(
                    text = it.toString(),
                    style = MaterialTheme.typography.bodyMedium.copy(color = Color.Black),
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }


            product?.description?.let {
                Text(text = it,
                    style = TextStyle(
                        color = SubTitleColor,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal
                    ),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = if (uiState.isTextExpanded) Int.MAX_VALUE else 3,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .animateContentSize()
                        .clickable { viewModel.onEvent(DetailsEvent.ToggleDescription) })
            }

            Text(
                text = "Gallery", style = TextStyle(
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                ),
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            LazyRow(
                modifier = Modifier.height(80.dp)
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 16.dp)
            ) {

                product?.images?.let { images ->
                    itemsIndexed(images) { index, item ->
                        ThumbnailImage(
                            imageUrl = item,
                            isSelected = uiState.selectedIndex == index,
                            onClick = {
                                viewModel.onEvent(DetailsEvent.SelectImage(index))
                                scope.launch {
                                    pagerState.animateScrollToPage(index, animationSpec = tween(durationMillis = 500))
                                }
                            }
                        )
                    }
                }

            }

            CustomSizeSection(
                modifier = Modifier.fillMaxWidth().wrapContentHeight(),
                selectedRegion = uiState.selectedRegion,
                selectedIndex = uiState.selectedSizeIndex,
                onRegionSelected = {
                     viewModel.onEvent(DetailsEvent.SelectRegion(it))
                },
                onSizeSelected = { size, index ->
                    viewModel.onEvent(DetailsEvent.SelectSize(size, index))
                }
            )


        }

    }

}
