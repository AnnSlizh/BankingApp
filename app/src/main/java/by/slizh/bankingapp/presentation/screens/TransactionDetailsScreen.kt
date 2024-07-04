package by.slizh.bankingapp.presentation.screens

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import by.slizh.bankingapp.presentation.components.TransactionDetailsTextField
import by.slizh.bankingapp.presentation.viewModels.transaction.TransactionEvent
import by.slizh.bankingapp.presentation.viewModels.transaction.TransactionViewModel
import by.slizh.bankingapp.ui.theme.Blue
import java.text.SimpleDateFormat
import java.util.Locale

data class TransactionField(
    val fieldLabel: String,
    var value: String
)

@Composable
fun TransactionDetailsScreen(
    navController: NavHostController,
    transactionViewModel: TransactionViewModel = hiltViewModel(),
    transactionId: Int
) {
    val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())

    val transactionState by transactionViewModel.state.collectAsState()
    val transaction = transactionState.currentTransaction

    LaunchedEffect(transactionId) {
        transactionViewModel.onEvent(TransactionEvent.GetTransactionById(transactionId))
    }

    val fields = remember(transaction) {
        listOf(
            TransactionField("Transaction was applied in", transaction?.company ?: "No data"),
            TransactionField("Transaction number", transaction?.transactionNumber ?: "No data"),
            TransactionField("Date", transaction?.date?.let { dateFormat.format(it) } ?: "No data"),
            TransactionField("Transaction status", transaction?.transactionStatus ?: "No data"),
            TransactionField("Amount", transaction?.amount.toString())
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 7.dp, start = 16.dp, end = 16.dp, bottom = 7.dp)
                .statusBarsPadding()
                .navigationBarsPadding()
        ) {
            Text(text = "Transaction", fontSize = 28.sp, color = Color.White)

            Spacer(modifier = Modifier.height(32.dp))

            fields.forEach { field ->
                TransactionDetailsTextField(
                    fieldLabel = field.fieldLabel,
                    value = field.value,
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Blue),
                shape = RoundedCornerShape(10.dp),
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Text(text = "Okay", fontSize = 17.sp)
            }
        }
    }
}
