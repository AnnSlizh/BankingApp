package by.slizh.bankingapp.domain.useCase.transaction

import by.slizh.bankingapp.domain.model.Transaction
import by.slizh.bankingapp.domain.repository.TransactionRepository

class GetTransactionById(private val transactionRepository: TransactionRepository) {
    operator fun invoke(transactionId: Int): Transaction {
        return transactionRepository.getTransactionById(transactionId)
    }
}