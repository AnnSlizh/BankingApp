package by.slizh.bankingapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import by.slizh.bankingapp.ui.theme.Blue
import by.slizh.bankingapp.ui.theme.LightGrey

@Composable
fun AddTransactionScreen() {

    var company by remember { mutableStateOf("") }
    var transactionNumber by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var transactionStatus by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 12.dp, start = 16.dp, end = 16.dp, bottom = 12.dp)
                .statusBarsPadding()
                .navigationBarsPadding()

        ) {
            Text(text = "Transaction", fontSize = 28.sp, color = Color.White)

            Spacer(modifier = Modifier.height(32.dp))

            Text(text = "Transaction was applied in", fontSize = 17.sp, color = Color.White)

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 16.dp),
                value = company,
                onValueChange = { company = it },
                shape = RoundedCornerShape(8.dp),
                textStyle = TextStyle(color = Color.White, fontSize = 15.sp)
            )

            Text(text = "Transaction number", fontSize = 17.sp, color = Color.White)

            OutlinedTextField(
                value = transactionNumber,
                onValueChange = { transactionNumber = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 16.dp),
                shape = RoundedCornerShape(8.dp),
                textStyle = TextStyle(color = Color.White, fontSize = 15.sp)
            )

            Text(text = "Date", fontSize = 17.sp, color = Color.White)

            OutlinedTextField(
                value = date,
                onValueChange = { date = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 16.dp),
                shape = RoundedCornerShape(8.dp),
                textStyle = TextStyle(color = Color.White, fontSize = 15.sp)
            )

            Text(text = "Transaction status", fontSize = 17.sp, color = Color.White)

            OutlinedTextField(
                value = transactionStatus,
                onValueChange = { transactionStatus = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 16.dp),
                shape = RoundedCornerShape(8.dp),
                textStyle = TextStyle(color = Color.White, fontSize = 15.sp)
            )

            Text(text = "Amount", fontSize = 17.sp, color = Color.White)

            OutlinedTextField(
                value = amount,
                onValueChange = { amount = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                shape = RoundedCornerShape(8.dp),
                textStyle = TextStyle(color = Color.White, fontSize = 15.sp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(modifier = Modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors(
                containerColor = Blue
            ), shape = RoundedCornerShape(10.dp), onClick = { /*TODO*/ }) {
                Text(text = "Okay", fontSize = 17.sp)
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun AddTransactionScreenPreview() {
    AddTransactionScreen()
}