package com.example.shoppieeclient.presentation.home.payment.components

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.shoppieeclient.R
import com.example.shoppieeclient.domain.payment.models.CardTypes
import com.example.shoppieeclient.domain.payment.models.PaymentCardModel
import com.example.shoppieeclient.ui.theme.LightGray
import com.example.shoppieeclient.ui.theme.PrimaryBlue
import com.example.shoppieeclient.utils.startsWithAny


private const val TAG = "CardItems"
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CardItem(
    card: PaymentCardModel,
    onClick: () -> Unit,
    onLongClick: () -> Unit,
    onSelectCard: () -> Unit,
) {

    val cardType = when {
        card.cardNumber.startsWithAny("5", "2") -> CardTypes.MASTERCARD
        card.cardNumber.startsWithAny("4") -> CardTypes.VISA
        card.cardNumber.startsWithAny("6", "8") -> CardTypes.RUPAY
        else -> CardTypes.NONE
    }


    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .height(100.dp)
            .combinedClickable(
                onClick = onClick,
                onLongClick = onLongClick
            ),
        shape = RoundedCornerShape(16.dp),
        color = LightGray,
        shadowElevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            RadioButton(
                selected = card.isSelected,
                onClick = onSelectCard,
                colors = RadioButtonDefaults.colors(
                    selectedColor = PrimaryBlue,
                    unselectedColor = Color.White.copy(alpha = 0.6f)
                )
            )
            Log.e(TAG, "CardItem: ${card.isSelected}", )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Image(
                        modifier = Modifier.size(width = 100.dp, height = 24.dp),
                        painter = painterResource(
                            when (cardType) {
                                CardTypes.MASTERCARD -> R.drawable.mastercard
                                CardTypes.RUPAY -> R.drawable.rupay
                                else -> R.drawable.visa
                            }
                        ),
                        contentDescription = "card"
                    )
                    Text(
                        text = "**** **** **** ${card.cardNumber.takeLast(4)}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White
                    )
                }

            }
        }
    }
}