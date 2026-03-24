package com.stockchef.app.presentation.settings

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.stockchef.app.ui.theme.StockChefTheme

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

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    StockChefTheme {
        SettingsScreen(
            onLogout = {}
        )
    }
}