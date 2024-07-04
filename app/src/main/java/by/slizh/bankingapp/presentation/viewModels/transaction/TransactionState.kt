package by.slizh.bankingapp.presentation.viewModels.transaction

import by.slizh.bankingapp.domain.model.Transaction

data class TransactionState(
    val transactions: List<Transaction> = emptyList(),
    val currentAccountId: Int? = null,
    val currentTransaction: Transaction? = null,
)