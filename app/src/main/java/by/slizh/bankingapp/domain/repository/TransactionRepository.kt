package by.slizh.bankingapp.domain.repository

import by.slizh.bankingapp.domain.model.Transaction
import kotlinx.coroutines.flow.Flow
import java.util.Date

interface TransactionRepository {

    fun getAllTransactions(accountId: Int): Flow<List<Transaction>>

    fun getTransactionsByDate(accountId: Int, startDate: Date, endDate: Date): Flow<List<Transaction>>

    fun getTransactionById(transactionId: Int): Transaction

    suspend fun insertTransaction(transaction: Transaction)
}