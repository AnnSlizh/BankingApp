package by.slizh.bankingapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "account")
data class Account(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val accountNumber: Long,
    val cardNumber: Long
)
