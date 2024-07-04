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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import by.slizh.bankingapp.presentation.viewModels.addTransaction.AddTransactionEvent
import by.slizh.bankingapp.presentation.viewModels.addTransaction.AddTransactionViewModel
import by.slizh.bankingapp.presentation.components.TransactionOutlinedTextField
import by.slizh.bankingapp.ui.theme.Blue

@Composable
fun AddTransactionScreen(
    navController: NavHostController,
    accountId: Int,
    addTransactionViewModel: AddTransactionViewModel = hiltViewModel()
) {
    val state by addTransactionViewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        addTransactionViewModel.onEvent(AddTransactionEvent.SetAccountId(accountId))
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

            TransactionOutlinedTextField(
                fieldLabel = "Transaction was applied in",
                value = state.company,
                onValueChange = {
                    addTransactionViewModel.onEvent(
                        AddTransactionEvent.EnterCompany(
                            it
                        )
                    )
                },
                isError = state.companyError

            )
            TransactionOutlinedTextField(
                fieldLabel = "Transaction number",
                value = state.transactionNumber,
                onValueChange = { addTransactionViewModel.onEvent(AddTransactionEvent.EnterNumber(it)) },
                isError = state.transactionNumberError
            )
            TransactionOutlinedTextField(
                fieldLabel = "Amount",
                value = state.amount,
                onValueChange = { addTransactionViewModel.onEvent(AddTransactionEvent.EnterAmount(it)) },
                isError = state.amountError || state.amountFormatError
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Blue),
                shape = RoundedCornerShape(10.dp),
                onClick = {
                    addTransactionViewModel.onEvent(AddTransactionEvent.ValidateFields)
                    val currentState = addTransactionViewModel.state.value
                    if (currentState.companyError ||
                        currentState.transactionNumberError ||
                        currentState.amountError || currentState.amountFormatError
                    ) {
                        return@Button
                    }
                    addTransactionViewModel.onEvent(AddTransactionEvent.AddTransaction)
                    navController.popBackStack()
                }
            ) {
                Text(text = "Okay", fontSize = 17.sp)
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun AddTransactionScreenPreview() {
    AddTransactionScreen(rememberNavController(), 1)
}