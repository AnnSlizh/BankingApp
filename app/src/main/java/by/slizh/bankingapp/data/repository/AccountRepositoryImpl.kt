package by.slizh.bankingapp.data.repository

import by.slizh.bankingapp.data.dao.AccountDao
import by.slizh.bankingapp.domain.model.Account
import by.slizh.bankingapp.domain.repository.AccountRepository
import kotlinx.coroutines.flow.Flow

class AccountRepositoryImpl(private val accountDao: AccountDao) : AccountRepository {
    override fun getAllAccounts(): Flow<List<Account>> = accountDao.getAllAccounts()

    override fun getAccountById(accountId: Int): Account = accountDao.getAccountById(accountId)

    override suspend fun insertAccount(account: Account) {
        accountDao.insertAccount(account)
    }
}