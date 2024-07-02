package by.slizh.bankingapp.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import by.slizh.bankingapp.presentation.components.TransactionListItem
import by.slizh.bankingapp.modelTest.accountsList
import by.slizh.bankingapp.modelTest.transactionsList
import by.slizh.bankingapp.navigation.Screen
import by.slizh.bankingapp.presentation.components.AccountBottomSheet
import by.slizh.bankingapp.presentation.components.AccountCard
import by.slizh.bankingapp.ui.theme.Blue
import by.slizh.bankingapp.ui.theme.DarkGrey

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavHostController) {

    var selectedAccount by remember { mutableStateOf(accountsList[0]) }
    var showAccountBottomSheet by remember { mutableStateOf(false) }

    Scaffold(containerColor = Color.Black, floatingActionButton = {
        FloatingActionButton(
            shape = CircleShape,
            containerColor = Blue,
            contentColor = Color.White,
            onClick = { navController.navigate(route = Screen.AddTransactionScreen.route) }) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add transaction")
        }
    }) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp, start = 16.dp, end = 16.dp, bottom = 12.dp)
                .statusBarsPadding()
                .navigationBarsPadding()

        ) {
            Text(text = "Account", fontSize = 28.sp, color = Color.White)
            Spacer(modifier = Modifier.height(16.dp))

            AccountCard(account = selectedAccount, onClick = { showAccountBottomSheet = true })

            if (showAccountBottomSheet) {
                AccountBottomSheet(
                    currentAccount = selectedAccount,
                    onAccountSelected = { account ->
                        selectedAccount = account
                        showAccountBottomSheet = false
                    }
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Recent Transactions", fontSize = 24.sp, color = Color.White)

                Text(
                    text = "VIEW ALL",
                    fontSize = 13.sp,
                    color = Blue,
                    modifier = Modifier.clickable(onClick = {
                        navController.navigate(route = Screen.AllTransactionsScreen.route)
                    })
                )
            }

            Spacer(modifier = Modifier.height(21.dp))

            Box(modifier = Modifier.clip(RoundedCornerShape(10.dp))) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(DarkGrey)
                ) {
                    items(transactionsList.takeLast(5)) { transaction ->
                        TransactionListItem(
                            transaction = transaction,
                            showDetailsTransaction = {
                                navController.navigate(
                                    route = Screen.TransactionDetailsScreen.createRoute(
                                        transaction.company
                                    )
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(rememberNavController())
}