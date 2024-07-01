package by.slizh.bankingapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.navigation.compose.rememberNavController
import by.slizh.bankingapp.navigation.Navigation
import by.slizh.bankingapp.ui.theme.BankingAppTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BankingAppTheme {
                val navController = rememberNavController()
                Navigation(navController = navController)
            }
        }
    }
}


