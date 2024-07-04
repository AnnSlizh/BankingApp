package by.slizh.bankingapp.presentation.viewModels.transaction

import by.slizh.bankingapp.domain.model.Transaction
import java.util.Date

sealed class TransactionEvent {
    data class GetTransactions(val accountId: Int) : TransactionEvent()
    data class GetTransactionById(val transactionId: Int) : TransactionEvent()
    data class GetTransactionsByDate(val accountId: Int, val startDate: Date, val endDate: Date) : TransactionEvent()
    data class AddTransaction(val transaction: Transaction) : TransactionEvent()
}