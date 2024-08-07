package by.slizh.bankingapp.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TransactionDetailsTextField(
    fieldLabel: String,
    value: String,
    modifier: Modifier = Modifier
) {

    Column(modifier = modifier) {
        Text(text = fieldLabel, fontSize = 17.sp, color = Color.White)
        OutlinedTextField(
            value = value,
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 16.dp),
            shape = RoundedCornerShape(8.dp),
            textStyle = TextStyle(color = Color.White, fontSize = 15.sp),
            readOnly = true
        )
    }
}