package by.slizh.bankingapp.navigation

sealed class Screen(val route: String) {
    object HomeScreen : Screen(route = "home_screen")
    object AddTransactionScreen : Screen(route = "add_transaction_screen")
    object AllTransactionsScreen : Screen(route = "all_transactions_screen")
    object TransactionDetailsScreen : Screen(route = "transaction_details_screen/{company}") {
        fun createRoute(company: String) = "transaction_details_screen/$company"
    }
}