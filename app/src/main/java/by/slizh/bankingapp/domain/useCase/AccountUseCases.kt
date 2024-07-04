package by.slizh.bankingapp.domain.useCase

import by.slizh.bankingapp.domain.useCase.account.AddAccount
import by.slizh.bankingapp.domain.useCase.account.GetAccountById
import by.slizh.bankingapp.domain.useCase.account.GetAllAccounts

data class AccountUseCases(
    val getAccountById: GetAccountById,
    val getAllAccounts: GetAllAccounts,
    val addAccount: AddAccount
)
