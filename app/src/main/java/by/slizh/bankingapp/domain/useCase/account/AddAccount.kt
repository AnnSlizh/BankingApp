package by.slizh.bankingapp.domain.useCase.account

import by.slizh.bankingapp.domain.model.Account
import by.slizh.bankingapp.domain.repository.AccountRepository

class AddAccount(private val accountRepository: AccountRepository) {
    suspend operator fun invoke(account: Account) {
        accountRepository.insertAccount(account)
    }
}