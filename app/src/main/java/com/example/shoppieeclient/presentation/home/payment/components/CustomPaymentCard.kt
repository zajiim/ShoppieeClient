package com.example.shoppieeclient.presentation.home.payment.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.shoppieeclient.R
import com.example.shoppieeclient.domain.payment.models.CardTypes
import com.example.shoppieeclient.domain.payment.models.PaymentCardModel
import com.example.shoppieeclient.presentation.home.payment.PaymentEvents
import com.example.shoppieeclient.presentation.home.payment.PaymentStates
import com.example.shoppieeclient.presentation.home.payment.PaymentViewModel
import com.example.shoppieeclient.ui.theme.DefaultCardColor
import com.example.shoppieeclient.ui.theme.LightGray
import com.example.shoppieeclient.ui.theme.MasterCardColor
import com.example.shoppieeclient.ui.theme.PrimaryBlue
import com.example.shoppieeclient.ui.theme.RupayCardColor
import com.example.shoppieeclient.ui.theme.VisaColor
import org.koin.androidx.compose.koinViewModel

private const val TAG = "CustomPaymentCard"
@Composable
fun CustomPaymentCard(
    modifier: Modifier = Modifier,
    viewModel: PaymentViewModel = koinViewModel()
) {

    val state = viewModel.paymentState
    val cardAnimatedColor by animateColorAsState(
        targetValue = when (state.cardType) {
            CardTypes.VISA -> VisaColor
            CardTypes.MASTERCARD -> MasterCardColor
            CardTypes.RUPAY -> RupayCardColor
            CardTypes.NONE -> DefaultCardColor
        }
    )
    val rotationAngle by animateFloatAsState(if (state.isBackVisibleState) 180f else 0f)
    Surface(modifier = modifier
        .padding(16.dp)
        .fillMaxWidth()
        .height(200.dp)
        .graphicsLayer { rotationY = rotationAngle }
        .clickable {
            viewModel.onEvent(PaymentEvents.OnCardClicked)
        }, shape = RoundedCornerShape(16.dp), color = cardAnimatedColor, shadowElevation = 10.dp
    ) {

        if (state.isBackVisibleState) {
            Column(
                modifier = Modifier
                    .padding(top = 46.dp)
                    .graphicsLayer { rotationY = 180f },
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(24.dp)
                        .background(Color.Black)
                        .padding(horizontal = 12.dp)
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 36.dp, bottom = 24.dp),
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = "Cvv", style = MaterialTheme.typography.labelSmall, color = LightGray
                    )
                    Text(
                        text = state.cvvText,
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.White
                    )
                }
            }
        } else {

            Column(
                modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 18.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {


                    Image(
                        painter = painterResource(R.drawable.chip),
                        contentDescription = "card chip",
                        modifier = Modifier.size(36.dp)
                    )
                    if (state.cardType == CardTypes.VISA) {
                        Image(
                            painter = painterResource(R.drawable.visa),
                            contentDescription = "visa card"
                        )
                    }
                }

                Text(
                    text = state.maskedCardNumber.chunked(4).joinToString(" "),
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White,
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .animateContentSize(spring())
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            "Card holder",
                            style = MaterialTheme.typography.labelSmall,
                            color = LightGray
                        )
                        Text(
                            state.nameText.uppercase(),
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.White
                        )
                    }
                    Column {
                        Text(
                            "Expiry", style = MaterialTheme.typography.labelSmall, color = LightGray
                        )
                        Text(
                            state.expiryText.chunked(2).joinToString("/"),
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.White
                        )
                    }
                    if (state.cardType == CardTypes.MASTERCARD) {
                        Image(
                            modifier = Modifier.padding(start = 16.dp),
                            painter = painterResource(R.drawable.mastercard),
                            contentDescription = "mastercard"
                        )
                    }
                }

            }

        }
    }

}