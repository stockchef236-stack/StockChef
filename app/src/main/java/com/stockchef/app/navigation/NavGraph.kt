package com.stockchef.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.stockchef.app.presentation.addedit.AddEditViewModel
import com.stockchef.app.presentation.addedit.AddEditViewModelFactory
import com.stockchef.app.presentation.addedit.AddUpdateStockScreen
import com.stockchef.app.presentation.auth.AuthScreen
import com.stockchef.app.presentation.home.HomeScreen
import com.stockchef.app.presentation.home.HomeViewModel
import com.stockchef.app.presentation.home.HomeViewModelFactory
import com.stockchef.app.presentation.settings.SettingsScreen
import com.stockchef.app.presentation.splash.SplashScreen
import com.stockchef.app.presentation.splash.SplashViewModel

@Composable
fun StockChefNavGraph() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Splash
    ) {

        composable<Splash> {

            val viewModel: SplashViewModel = viewModel()

            SplashScreen(
                viewModel = viewModel,
                onNavigateToHome = {
                    navController.navigate(Home) {
                        popUpTo<Splash> { inclusive = true }
                    }
                },
                onNavigateToAuth = {
                    navController.navigate(Auth) {
                        popUpTo<Splash> { inclusive = true }
                    }
                }
            )
        }

        composable<Auth> {

            AuthScreen(
                onAuthSuccess = {
                    navController.navigate(Home) {
                        popUpTo<Auth> { inclusive = true }
                    }
                }
            )
        }

        composable<Home> {

            val context = LocalContext.current

            val viewModel: HomeViewModel = viewModel(
                factory = HomeViewModelFactory(context)
            )

            HomeScreen(
                viewModel = viewModel,
                onAddClick = {
                    navController.navigate(AddEdit())
                },
                onItemClick = { id ->
                    navController.navigate(AddEdit(id))
                },
                onSettingsClick = {
                    navController.navigate(Settings)
                }
            )
        }

        composable<AddEdit> { backStackEntry ->

            val route = backStackEntry.toRoute<AddEdit>()

            val viewModel: AddEditViewModel = viewModel(
                factory = AddEditViewModelFactory(route.id)
            )

            AddUpdateStockScreen(
                viewModel = viewModel,
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable<Settings> {

            SettingsScreen(
                onLogout = {
                    navController.navigate(Auth) {
                        popUpTo<Home> { inclusive = true }
                    }
                }
            )
        }
    }
}