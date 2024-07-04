package by.slizh.bankingapp.presentation.viewModels.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.slizh.bankingapp.domain.model.Account
import by.slizh.bankingapp.domain.useCase.AccountUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val accountUseCases: AccountUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(AccountSate())
    val state: StateFlow<AccountSate> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            accountUseCases.getAllAccounts().collect { accounts ->
                if (accounts.isEmpty()) {
                    addInitialAccounts()
                } else {
                    _state.value = _state.value.copy(accounts = accounts)
                }
            }
        }
    }

    private suspend fun addInitialAccounts() {
        val initialAccounts = listOf(
            Account(id = 1, name = "Hope", accountNumber = 1682739475, cardNumber = 12345678),
            Account(id = 2, name = "Sunday", accountNumber = 987654321, cardNumber = 77726490),
            Account(id = 3, name = "Astronaut", accountNumber = 890354678, cardNumber = 67823412)
        )
        initialAccounts.forEach { account ->
            accountUseCases.addAccount(account)
        }
    }
}