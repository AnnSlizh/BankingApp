package by.slizh.bankingapp.components

import android.os.Build
import android.widget.CalendarView
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import by.slizh.bankingapp.R
import by.slizh.bankingapp.ui.theme.LightGrey
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarField(
    label: String,
    placeholder: String,
    selectedDate: String,
    onDateSelected: (String) -> Unit,
    isError: Boolean = false,
    isEndDate: Boolean = false,
    minDate: LocalDate? = null
) {
    var expanded by remember { mutableStateOf(false) }

    Text(text = label, fontSize = 17.sp, color = Color.White)
    Spacer(modifier = Modifier.height(8.dp))

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it },
    ) {
        OutlinedTextField(
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth(),
            value = selectedDate,
            onValueChange = {},
            readOnly = true,
            singleLine = true,
            placeholder = { Text(text = placeholder, color = LightGrey, fontSize = 15.sp) },
            shape = RoundedCornerShape(8.dp),
            textStyle = TextStyle(color = Color.White, fontSize = 15.sp),
            trailingIcon = {
                IconButton(onClick = { expanded = !expanded }) {
                    Image(
                        painter = painterResource(id = R.drawable.calendar_icon),
                        contentDescription = null
                    )
                }
            },
            isError = isError
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                AndroidView(
                    factory = { CalendarView(it) },
                    modifier = Modifier.background(Color.White),
                    update = { calendarView ->
                        if ((minDate != null) && isEndDate) {
                            calendarView.minDate = minDate.toEpochDay() * 86400000
                        }
                        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
                            val date = "$dayOfMonth.${month + 1}.$year"
                            onDateSelected(date)
                            expanded = false
                        }
                    }
                )
            }
        }
    }
}

