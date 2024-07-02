package by.slizh.bankingapp.di

import android.app.Application
import androidx.room.Room
import by.slizh.bankingapp.data.TransactionsDatabase
import by.slizh.bankingapp.data.repository.AccountRepositoryImpl
import by.slizh.bankingapp.data.repository.TransactionRepositoryImpl
import by.slizh.bankingapp.domain.repository.AccountRepository
import by.slizh.bankingapp.domain.repository.TransactionRepository
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
}