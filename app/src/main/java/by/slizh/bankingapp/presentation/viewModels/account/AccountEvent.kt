package by.slizh.bankingapp.presentation.viewModels.account

import by.slizh.bankingapp.domain.model.Account

sealed class AccountEvent {
    object GetAccounts : AccountEvent()
    data class CurrentAccount(val accountId: Int) : AccountEvent()
    data class AddAccount(val account: Account) : AccountEvent()
}