package by.slizh.bankingapp.presentation.viewModels.transaction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.slizh.bankingapp.domain.useCase.TransactionUseCases
import by.slizh.bankingapp.presentation.util.DateFormat
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val transactionUseCases: TransactionUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(TransactionState())
    var state: StateFlow<TransactionState> = _state.asStateFlow()

    fun onEvent(event: TransactionEvent) {
        when (event) {
            is TransactionEvent.GetTransactions -> {
                viewModelScope.launch(Dispatchers.IO) {
                    transactionUseCases.getAllTransactions(event.accountId)
                        .collect { transactions ->
                            _state.value = _state.value.copy(
                                transactions = transactions,
                                currentAccountId = event.accountId
                            )
                        }
                }
            }

            is TransactionEvent.GetTransactionById -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val transaction = transactionUseCases.getTransactionById(event.transactionId)
                    _state.value = _state.value.copy(currentTransaction = transaction)

                }
            }

            is TransactionEvent.GetTransactionsByDate -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val startDate = DateFormat.parseDate(event.startDate)
                    val endDate = DateFormat.parseDate(event.endDate)

                    if (startDate != null && endDate != null) {
                        transactionUseCases.getTransactionsByDate(
                            event.accountId,
                            startDate,
                            endDate
                        ).collect { transactions ->
                            _state.value = _state.value.copy(transactions = transactions)
                        }
                    }
                }
            }

            is TransactionEvent.AddTransaction -> {
                viewModelScope.launch(Dispatchers.IO) {
                    transactionUseCases.insertTransaction(event.transaction)
                }
            }
        }
    }
}