package by.slizh.bankingapp.presentation.viewModels.addTransaction

sealed class AddTransactionEvent {
    data class EnterCompany(val value: String) : AddTransactionEvent()
    data class EnterNumber(val value: String) : AddTransactionEvent()
    data class EnterAmount(val value: String) : AddTransactionEvent()
    data class SetAccountId(val accountId: Int) : AddTransactionEvent()
    object AddTransaction : AddTransactionEvent()
    object ValidateFields : AddTransactionEvent()
}