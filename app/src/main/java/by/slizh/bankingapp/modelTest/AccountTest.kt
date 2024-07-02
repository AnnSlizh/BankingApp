package by.slizh.bankingapp.modelTest

data class Account(
    val name: String,
    val accountNumber: Long,
    val cardNumber: Long
)

val accountsList = listOf(
    Account("Hope", 12345678, 12345678),
    Account("Ooooo", 12345678, 12345678),
    Account("Llll", 12345678, 12345678)
)
