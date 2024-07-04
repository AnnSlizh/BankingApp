package by.slizh.bankingapp.presentation.viewModels.addTransaction

data class AddTransactionState(
    val company: String = "",
    val transactionNumber: String = "",
    val amount: String = "",
    val currentAccountId: Int? = null,
    val currentTransactionId: Int? = null,
    val companyError: Boolean = false,
    val transactionNumberError: Boolean = false,
    val amountError: Boolean = false,
    val amountFormatError: Boolean = false
)
