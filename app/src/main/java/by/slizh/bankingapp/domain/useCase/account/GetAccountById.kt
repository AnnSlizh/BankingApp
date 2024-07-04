package by.slizh.bankingapp.domain.useCase.account

import by.slizh.bankingapp.domain.model.Account
import by.slizh.bankingapp.domain.repository.AccountRepository

class GetAccountById(private val accountRepository: AccountRepository) {
    operator fun invoke(accountId: Int): Account {
        return accountRepository.getAccountById(accountId)
    }
}