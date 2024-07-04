package by.slizh.bankingapp.domain.repository

import by.slizh.bankingapp.domain.model.Account
import kotlinx.coroutines.flow.Flow

interface AccountRepository {

    fun getAllAccounts(): Flow<List<Account>>

    fun getAccountById(accountId: Int): Account

    suspend fun insertAccount(account: Account)
}