package com.example.shoppieeclient.presentation.home.track_order.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shoppieeclient.R
import com.example.shoppieeclient.domain.home.track_order.models.OrderStep
import com.example.shoppieeclient.domain.home.track_order.models.StepColors
import com.example.shoppieeclient.domain.home.track_order.models.StepStatus
import com.example.shoppieeclient.ui.theme.PrimaryBlue

@Composable
fun StepItem(
    modifier: Modifier = Modifier,
    step: OrderStep,
    status: StepStatus,
    isLast: Boolean
) {
    val (circleColor, lineColor, textColor, dateColor, iconColor) = when (status) {
        StepStatus.COMPLETED -> StepColors(
            circleColor = PrimaryBlue,
            lineColor = PrimaryBlue,
            textColor = Color.Black,
            dateColor = Color.Gray,
            iconColor = Color.White
        )
        StepStatus.CURRENT -> StepColors(
            circleColor = PrimaryBlue,
            lineColor = Color.Gray.copy(alpha = 0.3f),
            textColor = Color.Black,
            dateColor = Color.Gray,
            iconColor = Color.White
        )
        StepStatus.PENDING -> StepColors(
            circleColor = Color.Gray.copy(alpha = 0.2f),
            lineColor = Color.Gray.copy(alpha = 0.3f),
            textColor = Color.Gray.copy(alpha = 0.6f),
            dateColor = Color.Gray.copy(alpha = 0.6f),
            iconColor = Color.Gray.copy(alpha = 0.6f)
        )
    }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Top
        ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(end = 16.dp, top = 8.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(circleColor),
                contentAlignment = Alignment.Center
            ) {
                if (status == StepStatus.COMPLETED) {
                    Icon(
                        painter = painterResource(R.drawable.ic_order_delivered_tick),
                        contentDescription = "Completed",
                        tint = iconColor,
                        modifier = Modifier.size(16.dp)
                    )
                } else {
                    Icon(
                        painter = painterResource(step.icon),
                        contentDescription = step.title,
                        tint = iconColor,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            if (!isLast) {
                Canvas(modifier = Modifier.width(2.dp).height(64.dp)) {
                    drawLine(
                        color = lineColor,
                        start = Offset(size.width/2, 0f),
                        end = Offset(size.width/2, size.height + 8.dp.toPx()),
                        strokeWidth = 4.dp.toPx()
                    )
                }
            }
        }

        Column(modifier = Modifier.weight(1f)) {
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = step.title,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Medium, color = textColor),
                    modifier = Modifier.weight(1f)
                )

                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(
                            if (status == StepStatus.PENDING)
                                Color.Gray.copy(alpha = 0.1f)
                            else
                                Color(0xFF92400E).copy(alpha = 0.1f)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(step.icon),
                        contentDescription = step.title,
                        tint = if (status == StepStatus.PENDING)
                            Color.Gray.copy(alpha = 0.6f)
                        else
                            PrimaryBlue,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
            Text(
                text = step.date,
                fontSize = 14.sp,
                color = dateColor,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }

}