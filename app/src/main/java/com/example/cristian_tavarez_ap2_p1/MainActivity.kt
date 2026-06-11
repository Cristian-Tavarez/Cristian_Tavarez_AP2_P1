package com.example.cristian_tavarez_ap2_p1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cristian_tavarez_ap2_p1.presentation.borrame.AmonestacionEditScreen
import com.example.cristian_tavarez_ap2_p1.presentation.borrame.AmonestacionListScreen
import com.example.cristian_tavarez_ap2_p1.presentation.borrame.AmonestacionViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExamenNavHost()
        }
    }
}

@Composable
fun ExamenNavHost(navController: NavHostController = rememberNavController()) {
    val viewModel: AmonestacionViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = "list_screen"
    ) {
        composable(route = "list_screen") {
            AmonestacionListScreen(
                viewModel = viewModel,
                onNavigateToForm = { navController.navigate("form_screen") }
            )
        }

        composable(route = "form_screen") {
            AmonestacionEditScreen(
                viewModel = viewModel,
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}