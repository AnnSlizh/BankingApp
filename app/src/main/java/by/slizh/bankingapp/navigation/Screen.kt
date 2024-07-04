package by.slizh.bankingapp.navigation

sealed class Screen(val route: String) {
    object HomeScreen : Screen(route = "home_screen")

    object AllTransactionsScreen : Screen(route = "all_transactions_screen/{accountId}") {
        fun createRoute(accountId: Int) = "all_transactions_screen/$accountId"
    }

    object TransactionDetailsScreen : Screen(route = "transaction_details_screen/{transactionId}") {
        fun createRoute(transactionId: Int) = "transaction_details_screen/$transactionId"
    }

    object AddTransactionScreen : Screen(route = "add_transaction_screen/{accountId}") {
        fun createRoute(accountId: Int) = "add_transaction_screen/$accountId"
    }
}