package by.slizh.bankingapp.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import by.slizh.bankingapp.modelTest.Account
import by.slizh.bankingapp.modelTest.accountsList
import by.slizh.bankingapp.ui.theme.DarkBlue
import by.slizh.bankingapp.ui.theme.DarkGrey
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountBottomSheet(
    currentAccount: Account,
    onAccountSelected: (Account) -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    ModalBottomSheet(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.Black,
        onDismissRequest = { onAccountSelected(currentAccount) },
        sheetState = sheetState
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 13.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
                .navigationBarsPadding()
        ) {
            Text(text = "Select the account", fontSize = 34.sp, color = Color.White)
            Spacer(modifier = Modifier.height(32.dp))

            Box(modifier = Modifier.fillMaxWidth()) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    items(accountsList) { account ->
                        val backgroundColor =
                            if (account == currentAccount) DarkBlue else DarkGrey
                        AccountCard(
                            account = account,
                            onClick = {
                                scope.launch { sheetState.hide() }.invokeOnCompletion {
                                    if (!sheetState.isVisible) {
                                        onAccountSelected(account)
                                    }
                                }
                            },
                            backgroundColor = backgroundColor
                        )
                    }
                }
            }
        }
    }
}