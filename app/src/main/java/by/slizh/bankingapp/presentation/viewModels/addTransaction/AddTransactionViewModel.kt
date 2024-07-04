package by.slizh.bankingapp.presentation.viewModels.addTransaction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.slizh.bankingapp.domain.model.Transaction
import by.slizh.bankingapp.domain.useCase.TransactionUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class AddTransactionViewModel @Inject constructor(
    private val transactionUseCases: TransactionUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(AddTransactionState())
    val state: StateFlow<AddTransactionState> = _state.asStateFlow()

    fun onEvent(event: AddTransactionEvent) {
        when (event) {
            is AddTransactionEvent.EnterCompany -> {
                _state.value = _state.value.copy(company = event.value)
            }

            is AddTransactionEvent.EnterNumber -> {
                _state.value = _state.value.copy(transactionNumber = event.value)
            }

            is AddTransactionEvent.EnterAmount -> {
                _state.value = _state.value.copy(amount = event.value)
            }

            is AddTransactionEvent.SetAccountId -> {
                _state.value = _state.value.copy(currentAccountId = event.accountId)
            }

            is AddTransactionEvent.ValidateFields -> {
                val isAmountValid = _state.value.amount.toDoubleOrNull() != null
                _state.value = _state.value.copy(
                    companyError = _state.value.company.isBlank(),
                    transactionNumberError = _state.value.transactionNumber.isBlank(),
                    amountError = _state.value.amount.isBlank(),
                    amountFormatError = !isAmountValid
                )
            }

            is AddTransactionEvent.AddTransaction -> {
                val isAmountValid = _state.value.amount.toDoubleOrNull() != null
                if (_state.value.company.isNotBlank() &&
                    _state.value.transactionNumber.isNotBlank() &&
                    _state.value.amount.isNotBlank() &&
                    isAmountValid
                ) {
                    viewModelScope.launch {
                        val transaction = Transaction(
                            id = 0,
                            company = _state.value.company,
                            transactionNumber = _state.value.transactionNumber,
                            date = Date(),
                            transactionStatus = "Executed",
                            amount = _state.value.amount.toDouble(),
                            accountId = _state.value.currentAccountId!!
                        )
                        transactionUseCases.insertTransaction(transaction)
                        _state.value = AddTransactionState()
                    }
                }
            }
        }
    }
}