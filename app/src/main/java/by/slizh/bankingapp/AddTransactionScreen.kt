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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import by.slizh.bankingapp.components.TransactionOutlinedTextField
import by.slizh.bankingapp.ui.theme.Blue


data class TextFieldData(
    val fieldLabel: String,
    var value: MutableState<String>
)

@Composable
fun AddTransactionScreen() {

    val textFieldDataList = remember {
        mutableStateListOf(
            TextFieldData("Transaction was applied in", mutableStateOf("")),
            TextFieldData("Transaction number", mutableStateOf("")),
            TextFieldData("Date", mutableStateOf("")),
            TextFieldData("Transaction status", mutableStateOf("")),
            TextFieldData("Amount", mutableStateOf(""))
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

            textFieldDataList.forEach { data ->
                TransactionOutlinedTextField(
                    fieldLabel = data.fieldLabel,
                    value = data.value.value,
                    onValueChange = { newValue ->
                        data.value.value = newValue
                    }
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Blue),
                shape = RoundedCornerShape(10.dp),
                onClick = { /*TODO*/ }
            ) {
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