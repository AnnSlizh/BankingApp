package by.slizh.bankingapp.modelTest

data class Transaction(
    val company: String,
    val transactionNumber: String,
    val date: String,
    val transactionStatus: String,
    val amount: String
)

val transactionsList = listOf(
    Transaction("Apple", "eee4rrrrJK", "01.03.2024", "Executed", "$10.09"),
    Transaction("Day", "eee4rrrrJK", "01.03.2024", "Executed", "$10.09"),
    Transaction("Road", "eee4rrrrJK", "01.03.2024", "Executed", "$10.09"),
    Transaction("Lord", "eee4rrrrJK", "01.03.2024", "Executed", "$10.09"),
    Transaction("Harry", "eee4rrrrJK", "01.03.2024", "Executed", "$10.09"),
    Transaction("Anne", "eee4rrrrJK", "01.03.2024", "Executed", "$10.09"),
    Transaction("Help", "eee4rrrrJK", "01.03.2024", "Executed", "$10.09")
)