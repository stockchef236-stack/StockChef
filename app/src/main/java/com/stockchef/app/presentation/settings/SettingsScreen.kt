package com.stockchef.app.presentation.settings

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SettingsScreen(
    onLogout: () -> Unit
) {

    val context = LocalContext.current

    val viewModel: SettingsViewModel = viewModel(
        factory = SettingsViewModelFactory(context)
    )

    SettingsContent(
        onLogoutClick = {
            viewModel.logout {
                onLogout()
            }
        },
        onClearCacheClick = {
            viewModel.clearCache()
        }
    )
}