package by.slizh.bankingapp.di

import android.app.Application
import androidx.room.Room
import by.slizh.bankingapp.data.TransactionsDatabase
import by.slizh.bankingapp.data.repository.AccountRepositoryImpl
import by.slizh.bankingapp.data.repository.TransactionRepositoryImpl
import by.slizh.bankingapp.domain.repository.AccountRepository
import by.slizh.bankingapp.domain.repository.TransactionRepository
import by.slizh.bankingapp.domain.useCase.AccountUseCases
import by.slizh.bankingapp.domain.useCase.TransactionUseCases
import by.slizh.bankingapp.domain.useCase.account.AddAccount
import by.slizh.bankingapp.domain.useCase.account.GetAccountById
import by.slizh.bankingapp.domain.useCase.account.GetAllAccounts
import by.slizh.bankingapp.domain.useCase.transaction.GetAllTransactions
import by.slizh.bankingapp.domain.useCase.transaction.GetTransactionById
import by.slizh.bankingapp.domain.useCase.transaction.GetTransactionsByDate
import by.slizh.bankingapp.domain.useCase.transaction.InsertTransaction
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTransactionsDatabase(application: Application): TransactionsDatabase {
        return Room.databaseBuilder(
            application,
            TransactionsDatabase::class.java,
            TransactionsDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideTransactionRepository(database: TransactionsDatabase): TransactionRepository {
        return TransactionRepositoryImpl(database.transactionDao)
    }

    @Provides
    @Singleton
    fun provideAccountRepository(database: TransactionsDatabase): AccountRepository {
        return AccountRepositoryImpl(database.accountDao)
    }

    @Provides
    @Singleton
    fun provideAccountUseCases(accountRepository: AccountRepository): AccountUseCases {
        return AccountUseCases(
            getAccountById = GetAccountById(accountRepository),
            getAllAccounts = GetAllAccounts(accountRepository),
            addAccount = AddAccount(accountRepository)
        )
    }

    @Provides
    @Singleton
    fun provideTransactionUseCases(transactionRepository: TransactionRepository): TransactionUseCases {
        return TransactionUseCases(
            getAllTransactions = GetAllTransactions(transactionRepository),
            getTransactionById = GetTransactionById(transactionRepository),
            getTransactionsByDate = GetTransactionsByDate(transactionRepository),
            insertTransaction = InsertTransaction(transactionRepository)
        )
    }
}