package com.example.shoppieeclient.presentation.home.home.components

import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.shoppieeclient.domain.home.home.models.HomeProductModel
import com.example.shoppieeclient.ui.theme.Primary
import com.example.shoppieeclient.ui.theme.PrimaryBlue
import com.example.shoppieeclient.ui.theme.TitleColor
import com.example.shoppieeclient.utils.shimmerEffect
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

@Composable
fun NewArrivalsItem(
    modifier: Modifier = Modifier,
    leadingTitle: String,
    trailingTitle: String,
    shoes: List<HomeProductModel>?,
    isLoading: Boolean,
    userScrollable: Boolean
) {
    val pagerState = rememberPagerState(pageCount = { shoes?.size ?: 0 })
    val scope = rememberCoroutineScope()

    LaunchedEffect(pagerState) {
        if (pagerState.pageCount > 0) {
            scope.launch {
                while (isActive) {
                    delay(3000L)
                    val nextPage = (pagerState.currentPage + 1) % pagerState.pageCount
                    pagerState.animateScrollToPage(
                        page = nextPage,
                        animationSpec = tween(600)
                    )
                }
            }
        } else {
            Log.e("PagerState", "No pages available to display")
        }
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        if (!shoes.isNullOrEmpty()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth().padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = leadingTitle,
                    style = MaterialTheme.typography.bodyMedium.copy(color = TitleColor)
                )
                Text(
                    text = trailingTitle,
                    style = MaterialTheme.typography.bodySmall.copy(color = Primary)
                )
            }


            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize()
                    .height(120.dp),
                userScrollEnabled = userScrollable
            ) { pageIndex ->
                if (isLoading) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Gray.copy(alpha = 0.1f))
                            .shimmerEffect()
                    )
                } else {
                    val shoe = shoes[pageIndex]
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color.White),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxHeight()
                                .padding(8.dp),
                            verticalArrangement = Arrangement.SpaceEvenly,
                        ) {
                            Text(
                                text = "Best Seller",
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Normal,
                                    color = PrimaryBlue
                                )
                            )
                            Text(
                                text = shoe.name,
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = TitleColor
                                )
                            )
                            Text(text = "$${shoe.price}")
                        }
                        AsyncImage(
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(140.dp)
                                .padding(16.dp)
                                .clip(RoundedCornerShape(8.dp)),
                            model = shoe.images[0],
                            contentDescription = shoe.name,
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
            Row(
                Modifier
                    .height(50.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(pagerState.pageCount) { index ->
                    val isSelected = pagerState.currentPage == index
                    val width by animateDpAsState(
                        targetValue = if (isSelected) 16.dp else 4.dp,
                        animationSpec = tween(300), label = "width"
                    )

                    val color by animateColorAsState(
                        targetValue = if (isSelected) PrimaryBlue else PrimaryBlue.copy(alpha = 0.2f),
                        animationSpec = tween(300), label = "color"
                    )
                    Box(
                        modifier = Modifier
                            .padding(2.dp)
                            .width(width)
                            .height(4.dp)
                            .clip(CircleShape)
                            .background(color)

                    )
                }
            }
        }
    }

}