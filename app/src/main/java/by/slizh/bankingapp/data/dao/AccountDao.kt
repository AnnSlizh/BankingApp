package by.slizh.bankingapp.data.dao

import androidx.room.Dao
import androidx.room.Query
import by.slizh.bankingapp.domain.model.Account
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountDao {

    @Query("SELECT * FROM account")
    fun getAllAccounts(): Flow<List<Account>>

    @Query("SELECT * FROM account WHERE id = :accountId")
    fun getAccountById(accountId: Int): Account
}