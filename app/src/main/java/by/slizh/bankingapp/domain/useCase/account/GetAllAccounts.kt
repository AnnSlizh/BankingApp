package by.slizh.bankingapp.domain.useCase.account

import by.slizh.bankingapp.domain.model.Account
import by.slizh.bankingapp.domain.repository.AccountRepository
import kotlinx.coroutines.flow.Flow

class GetAllAccounts(private val accountRepository: AccountRepository) {
    operator fun invoke(): Flow<List<Account>>{
        return accountRepository.getAllAccounts()
    }
}