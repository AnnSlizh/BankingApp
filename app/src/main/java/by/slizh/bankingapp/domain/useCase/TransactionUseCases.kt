package by.slizh.bankingapp.domain.useCase

import by.slizh.bankingapp.domain.useCase.transaction.GetAllTransactions
import by.slizh.bankingapp.domain.useCase.transaction.GetTransactionById
import by.slizh.bankingapp.domain.useCase.transaction.GetTransactionsByDate
import by.slizh.bankingapp.domain.useCase.transaction.InsertTransaction

data class TransactionUseCases(
    val getAllTransactions: GetAllTransactions,
    val getTransactionById: GetTransactionById,
    val getTransactionsByDate: GetTransactionsByDate,
    val insertTransaction: InsertTransaction
)
