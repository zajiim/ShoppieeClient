package com.example.shoppieeclient.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.shoppieeclient.R
import com.example.shoppieeclient.presentation.home.bottom_nav_bar.listRoutes
import com.example.shoppieeclient.presentation.navigation.Destination
import com.example.shoppieeclient.ui.theme.ScaffoldBgColor
import com.example.shoppieeclient.ui.theme.ScaffoldTextColorFaded


@Composable
fun SideMenu(
    alpha: Float,
    onNavigate: (Destination) -> Unit,
    width: Dp,
    userName: String?,
    userProfileImage: String?,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(ScaffoldBgColor)
            .padding(horizontal = 20.dp)
            .graphicsLayer(alpha = alpha)
    ) {
        AsyncImage(
            modifier = Modifier
                .padding(top = 54.dp)
                .size(64.dp)
                .clip(CircleShape),
            model = userProfileImage ?: painterResource(R.drawable.user_image),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )

        Spacer(Modifier.height(24.dp))
        Text(
            text = "Hey, 👋",
            color = ScaffoldTextColorFaded,
            style = MaterialTheme.typography.titleSmall
        )
        Spacer(Modifier.height(6.dp))
        Text(
            text = userName ?: "",
            color = Color.White,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(Modifier.height(50.dp))

        listRoutes.forEach { item ->
            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable {
                    onNavigate(item.destination)
                }) {
                IconButton(onClick = { /*onNavigate(item.destination)*/ }) {
                    when {
                        item.title == "Cart" -> Icon(
                            painter = painterResource(id = R.drawable.ic_cart),
                            contentDescription = item.title,
                            tint = ScaffoldTextColorFaded
                        )

                        item.icon != null -> Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = item.title,
                            tint = ScaffoldTextColorFaded
                        )
                    }
                }
                Text(text = item.title, color = Color.White)
            }
        }

        Spacer(Modifier.height(50.dp))
        Spacer(
            Modifier
                .height(2.dp)
                .background(ScaffoldTextColorFaded)
                .width(width * 0.4f)
        )
        Spacer(Modifier.height(50.dp))

        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.clickable { }) {
            Icon(
                painter = painterResource(R.drawable.ic_sign_out),
                contentDescription = null,
                tint = ScaffoldTextColorFaded
            )
            Text("Sign out", color = Color.White)
        }
    }
}
