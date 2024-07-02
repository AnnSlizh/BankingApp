package by.slizh.bankingapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import by.slizh.bankingapp.data.dao.AccountDao
import by.slizh.bankingapp.data.dao.TransactionDao
import by.slizh.bankingapp.domain.model.Account
import by.slizh.bankingapp.domain.model.Transaction

@Database(entities = [Transaction::class, Account::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class TransactionsDatabase : RoomDatabase() {

    abstract val transactionDao: TransactionDao
    abstract val accountDao: AccountDao

    companion object {
        const val DATABASE_NAME = "transactions_db"
    }
}