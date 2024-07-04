package by.slizh.bankingapp.domain.useCase.transaction

import by.slizh.bankingapp.domain.model.Transaction
import by.slizh.bankingapp.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import java.util.Date

class GetTransactionsByDate(private val transactionRepository: TransactionRepository) {
    operator fun invoke(accountId: Int, startDate: Date, endDate: Date): Flow<List<Transaction>> {
        return transactionRepository.getTransactionsByDate(accountId, startDate, endDate)
    }
}