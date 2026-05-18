package com.example.raithabharosahub.ui.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Onboarding1 : Screen("onboarding1")
    object Onboarding2 : Screen("onboarding2")
    object LanguageSelection : Screen("language_selection")
    object Registration : Screen("registration")
    object LocationAccess : Screen("location_access")
    object Dashboard : Screen("dashboard")
    object Weather : Screen("weather")
    object Calendar : Screen("calendar")
    object SoilMonitoring : Screen("soil_monitoring")
    object CropHistory : Screen("crop_history")
    object Notifications : Screen("notifications")
    object Settings : Screen("settings")
    object Map : Screen("map")
    object ActionPlan : Screen("action_plan_route")
    object Profile : Screen("profile_route")
}
