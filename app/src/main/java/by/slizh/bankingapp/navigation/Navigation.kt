package by.slizh.bankingapp.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import by.slizh.bankingapp.presentation.screens.AddTransactionScreen
import by.slizh.bankingapp.presentation.screens.AllTransactionsScreen
import by.slizh.bankingapp.presentation.screens.HomeScreen
import by.slizh.bankingapp.presentation.screens.TransactionDetailsScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation(navController: NavHostController) {

    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(navController = navController)
        }

        composable(
            route = Screen.AllTransactionsScreen.route,
            arguments = listOf(navArgument("accountId") {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            val accountId = backStackEntry.arguments?.getInt("accountId")
            if (accountId != null) AllTransactionsScreen(
                navController = navController,
                accountId = accountId
            )
        }

        composable(
            route = Screen.AddTransactionScreen.route,
            arguments = listOf(navArgument("accountId") {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            val accountId = backStackEntry.arguments?.getInt("accountId")
            if (accountId != null) AddTransactionScreen(
                navController = navController,
                accountId = accountId
            )
        }

        composable(
            route = Screen.TransactionDetailsScreen.route,
            arguments = listOf(navArgument("transactionId") {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            val transactionId = backStackEntry.arguments?.getInt("transactionId")
            if (transactionId != null) TransactionDetailsScreen(
                navController = navController,
                transactionId = transactionId
            )

        }
    }
}