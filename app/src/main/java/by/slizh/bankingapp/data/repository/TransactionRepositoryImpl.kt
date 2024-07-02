package by.slizh.bankingapp.data.repository

import by.slizh.bankingapp.data.dao.TransactionDao
import by.slizh.bankingapp.domain.model.Transaction
import by.slizh.bankingapp.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import java.util.Date

class TransactionRepositoryImpl(private val transactionDao: TransactionDao) :
    TransactionRepository {

    override fun getAllTransactions(accountId: Int): Flow<List<Transaction>> =
        transactionDao.getAllTransactions(accountId)

    override fun getTransactionsByDate(
        accountId: Int,
        startDate: Date,
        endDate: Date
    ): Flow<List<Transaction>> = transactionDao.getTransactionsByDate(accountId, startDate, endDate)


    override fun getTransactionById(transactionId: Int): Transaction =
        transactionDao.getTransactionById(transactionId)

    override suspend fun insertTransaction(transaction: Transaction) =
        transactionDao.insertTransaction(transaction)

}