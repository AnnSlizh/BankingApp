package by.slizh.bankingapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import by.slizh.bankingapp.R
import by.slizh.bankingapp.model.Transaction
import by.slizh.bankingapp.ui.theme.Green
import by.slizh.bankingapp.ui.theme.LightGrey

@Composable
fun TransactionListItem(transaction: Transaction, showDetailsTransaction: () -> Unit) {

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable(
                onClick = { showDetailsTransaction() }
            )
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = transaction.company, fontSize = 17.sp, color = Color.White)
            Text(
                text = transaction.date,
                fontSize = 13.sp,
                color = Color.White.copy(alpha = 0.6f)
            )
            Text(
                text = transaction.transactionStatus,
                fontSize = 13.sp,
                color = Green
            )
        }

        Row(
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 8.dp)
        ) {
            Text(
                text = transaction.amount,
                fontSize = 17.sp,
                color = Color.White,
                modifier = Modifier.padding(end = 8.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.indicator),
                contentDescription = null
            )
        }
    }

    HorizontalDivider(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp),
        thickness = 1.dp,
        color = LightGrey
    )
}
