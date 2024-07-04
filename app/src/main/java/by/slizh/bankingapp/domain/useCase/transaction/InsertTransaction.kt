package by.slizh.bankingapp.domain.useCase.transaction

import by.slizh.bankingapp.domain.model.Transaction
import by.slizh.bankingapp.domain.repository.TransactionRepository

class InsertTransaction(private val transactionRepository: TransactionRepository) {
    suspend operator fun invoke(transaction: Transaction) {
        return transactionRepository.insertTransaction(transaction)
    }
}