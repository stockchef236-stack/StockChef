package com.stockchef.app.navigation

import kotlinx.serialization.Serializable

@Serializable
object Splash

@Serializable
object Home

@Serializable
object Auth

@Serializable
data class AddEdit(val id: String? = null)