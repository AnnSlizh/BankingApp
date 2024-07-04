package by.slizh.bankingapp.presentation.viewModels.account

import by.slizh.bankingapp.domain.model.Account

data class AccountSate(
    val accounts: List<Account> = emptyList(),
    val currentAccount: Account? = null
)
