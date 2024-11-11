package com.example.shoppieeclient.presentation.home.details

import android.util.Log
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shoppieeclient.presentation.common.components.CustomLineProgressIndicator
import com.example.shoppieeclient.presentation.home.details.components.AddCartBottomSection
import com.example.shoppieeclient.presentation.home.details.components.CustomNavigationTopAppBar
import com.example.shoppieeclient.presentation.home.details.components.CustomSizeSection
import com.example.shoppieeclient.presentation.home.details.components.ProductImage
import com.example.shoppieeclient.presentation.home.details.components.ThumbnailImage
import com.example.shoppieeclient.ui.theme.BackGroundColor
import com.example.shoppieeclient.ui.theme.PrimaryBlue
import com.example.shoppieeclient.ui.theme.SubTitleColor
import com.example.shoppieeclient.utils.shimmerEffect
import kotlinx.coroutines.launch

private const val TAG = "DetailsScreen"

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: DetailsViewModel,
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
    onNavigateClick: () -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope
) {

    val uiState = viewModel.uiState
    val product = uiState.details
    val scope = rememberCoroutineScope()
    Log.d(TAG, "DetailsScreen: product name == ${product?.name}")


    val pagerState = rememberPagerState(pageCount = { product?.images?.size ?: 1 })

    LaunchedEffect(pagerState.currentPage) {
        viewModel.onEvent(DetailsEvent.SelectImage(pagerState.currentPage))
    }

    with(sharedTransitionScope) {
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
                if (uiState.isLoading) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .shimmerEffect()
                    )
                } else {
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
                                animatedVisibilityScope = animatedVisibilityScope
                            )
                        }
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
                        modifier = Modifier
                            .sharedElement(
                                state = rememberSharedContentState(key = it),
                                animatedVisibilityScope = animatedVisibilityScope
                            )
                            .padding(horizontal = 16.dp)
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
                    modifier = Modifier
                        .height(80.dp)
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
                                        pagerState.animateScrollToPage(
                                            index,
                                            animationSpec = tween(durationMillis = 500)
                                        )
                                    }
                                }
                            )
                        }
                    }

                }

                CustomSizeSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    selectedRegion = uiState.selectedRegion,
                    selectedIndex = uiState.selectedSizeIndex,
                    onRegionSelected = {
                        viewModel.onEvent(DetailsEvent.SelectRegion(it))
                    },
                    onSizeSelected = { size, index ->
                        viewModel.onEvent(DetailsEvent.SelectSize(size, index))
                    }
                )
                Spacer(modifier = Modifier.height(160.dp))

            }


            CustomNavigationTopAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                title = uiState.details?.name.toString(),
                navigationIcon = {
                    IconButton(
                        onClick = onNavigateClick,
                        modifier = Modifier
                            .wrapContentSize()
                            .clip(CircleShape)
                            .background(Color.White)
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = "Go back"
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = { },
                        modifier = Modifier
                            .wrapContentSize()
                            .clip(CircleShape)
                            .background(Color.White)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.FavoriteBorder,
                            contentDescription = "Favorite icon"
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )


            AddCartBottomSection(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .wrapContentSize(Alignment.BottomCenter),
                price = product?.price.toString(),
                selectedRegion = uiState.selectedRegion,
                selectedSize = uiState.selectedSize,
                sharedTransitionScope = sharedTransitionScope,
                animatedVisibilityScope = animatedVisibilityScope,
                isInCart = uiState.isAddedToCart,
                productId = product?.productId.toString(),

                onAddToCartClick = { productId, productSize, selectedRegion ->
                    Log.e(TAG, "DetailsScreen: pid: $productId, psize= $productSize ", )
                    viewModel.onEvent(DetailsEvent.AddToCart(productId,productSize, selectedRegion))
                }
            )

            if (uiState.isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.3f)),
                    contentAlignment = Alignment.Center
                ) {
                    CustomLineProgressIndicator(color = MaterialTheme.colorScheme.primary)
                }
            }

        }
    }

}
