package by.slizh.bankingapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import by.slizh.bankingapp.R
import by.slizh.bankingapp.model.Account
import by.slizh.bankingapp.ui.theme.DarkGrey

@Composable
fun AccountCard(account: Account, onClick: () -> Unit, backgroundColor: Color = DarkGrey) {

    Card(
        shape = RoundedCornerShape(13.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Image(
                painter = painterResource(id = R.drawable.card), contentDescription = null,
                modifier = Modifier
                    .width(40.dp)
                    .height(20.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp)
                    .weight(1f)
            ) {
                Text(text = account.name, fontSize = 15.sp, color = Color.White)
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = account.accountNumber.toString(),
                    fontSize = 15.sp,
                    color = Color.White.copy(alpha = 0.6f)
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = ("*".repeat(account.cardNumber.toString().length - 4) + account.cardNumber.toString()
                        .takeLast(4)),
                    fontSize = 15.sp,
                    color = Color.White.copy(alpha = 0.6f)
                )
            }

            Box(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.indicator),
                    contentDescription = null
                )
            }
        }
    }
}