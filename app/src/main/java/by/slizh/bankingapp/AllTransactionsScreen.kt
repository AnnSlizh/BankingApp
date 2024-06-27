package by.slizh.bankingapp

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import by.slizh.bankingapp.components.CalendarField
import by.slizh.bankingapp.ui.theme.Blue
import by.slizh.bankingapp.ui.theme.DarkGrey
import by.slizh.bankingapp.ui.theme.LightGrey
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "RememberReturnType")
@Composable
fun AllTransactionsScreen() {

    var startDate by remember { mutableStateOf("") }
    var endDate by remember { mutableStateOf("") }
    var startLocalDate by remember { mutableStateOf<LocalDate?>(null) }
    var startDateError by remember { mutableStateOf(false) }
    var endDateError by remember { mutableStateOf(false) }

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showFilterBottomSheet by remember { mutableStateOf(false) }

    Scaffold(
        containerColor = Color.Black,
        topBar = {
            CenterAlignedTopAppBar(
                colors = topAppBarColors(
                    containerColor = Color.Black,
                ),
                title = {
                    Text(
                        text = "All transactions", color = Color.White, fontSize = 17.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Image(
                            painter = painterResource(id = R.drawable.back_icon),
                            contentDescription = "Return to home screen"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { showFilterBottomSheet = true }) {
                        Image(
                            painter = painterResource(id = R.drawable.filter_icon),
                            contentDescription = "Open filter by date"
                        )
                    }
                }
            )
        },
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .padding(paddingValues)
                .padding(start = 12.dp, end = 12.dp, top = 8.dp)
                .clip(RoundedCornerShape(10.dp))
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(DarkGrey)
            ) {
                items(10) {
                    TransactionListItem(showDetailsTransaction = {/*TODO*/ })
                }
            }
        }

        if (showFilterBottomSheet) {
            ModalBottomSheet(
                modifier = Modifier.fillMaxSize(),
                containerColor = Color.Black,
                onDismissRequest = {
                    showFilterBottomSheet = false
                },
                sheetState = sheetState
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 13.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
                        .navigationBarsPadding()
                ) {

                    Text(text = "Filter by date", fontSize = 34.sp, color = Color.White)
                    Spacer(modifier = Modifier.height(32.dp))

                    CalendarField(
                        label = "Start date",
                        placeholder = "Select start date",
                        selectedDate = startDate,
                        onDateSelected = { date ->
                            startDate = date
                            startLocalDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("d.M.yyyy"))
                            startDateError = false
                            if (endDate.isNotEmpty() && startLocalDate != null && startLocalDate!!.isAfter(
                                    LocalDate.parse(endDate, DateTimeFormatter.ofPattern("d.M.yyyy"))
                                )
                            ) {
                                endDate = ""
                                endDateError = true
                            }
                        },
                        isError = startDateError
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    CalendarField(
                        label = "End date",
                        placeholder = "Select end date",
                        selectedDate = endDate,
                        onDateSelected = { date ->
                            endDate = date
                            endDateError = false
                        },
                        isError = endDateError,
                        isEndDate = true,
                        minDate = startLocalDate
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    HorizontalDivider(
                        thickness = 1.dp,
                        color = LightGrey
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = Blue),
                        shape = RoundedCornerShape(13.dp),
                        onClick = {
                            if (startDate.isEmpty()) {
                                startDateError = true
                            }
                            if (endDate.isEmpty()) {
                                endDateError = true
                            }
                            if (startDate.isNotEmpty() && endDate.isNotEmpty()) {
                                scope.launch { sheetState.hide() }.invokeOnCompletion {
                                    if (!sheetState.isVisible) {
                                        showFilterBottomSheet = false
                                    }
                                }
                            }
                        }
                    ) {
                        Text("Submit", fontSize = 17.sp, color = Color.White)
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showSystemUi = true)
@Composable
fun AllTransactionsScreenPreview() {
    AllTransactionsScreen()
}