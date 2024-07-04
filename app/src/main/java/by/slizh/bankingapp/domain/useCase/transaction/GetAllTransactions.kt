package by.slizh.bankingapp.domain.useCase.transaction

import by.slizh.bankingapp.domain.model.Transaction
import by.slizh.bankingapp.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow

class GetAllTransactions(private val transactionRepository: TransactionRepository) {
    operator fun invoke(accountId: Int): Flow<List<Transaction>> {
        return transactionRepository.getAllTransactions(accountId)
    }
}