package com.example.raithabharosahub.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.raithabharosahub.ui.screens.splash.SplashScreen
import com.example.raithabharosahub.ui.screens.onboarding.Onboarding1Screen
import com.example.raithabharosahub.ui.screens.onboarding.Onboarding2Screen
import com.example.raithabharosahub.ui.screens.language.LanguageSelectionScreen
import com.example.raithabharosahub.ui.screens.registration.RegistrationScreen
import com.example.raithabharosahub.ui.screens.location.LocationAccessScreen
import com.example.raithabharosahub.ui.screens.dashboard.DashboardScreen
import com.example.raithabharosahub.ui.screens.weather.WeatherScreen
import com.example.raithabharosahub.ui.screens.calendar.CalendarScreen
import com.example.raithabharosahub.ui.screens.history.CropHistoryScreen
import com.example.raithabharosahub.ui.screens.settings.SettingsScreen
import com.example.raithabharosahub.ui.screens.assistant.AiAssistantScreen
import com.example.raithabharosahub.ui.screens.soil.SoilMonitoringScreen
import com.example.raithabharosahub.ui.screens.map.MapScreen
import com.example.raithabharosahub.ui.screens.notifications.NotificationsScreen
import com.example.raithabharosahub.ui.screens.assistant.VoiceCommandScreen
import com.example.raithabharosahub.ui.screens.assistant.MicrophoneAccessScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(navController = navController)
        }
        composable(Screen.Onboarding1.route) {
            Onboarding1Screen(navController = navController)
        }
        composable(Screen.Onboarding2.route) {
            Onboarding2Screen(navController = navController)
        }
        composable(Screen.LanguageSelection.route) {
            LanguageSelectionScreen(navController = navController)
        }
        composable(Screen.Registration.route) {
            RegistrationScreen(navController = navController)
        }
        composable(Screen.LocationAccess.route) {
            LocationAccessScreen(navController = navController)
        }
        composable(Screen.Dashboard.route) {
            DashboardScreen(navController = navController)
        }
        composable(Screen.Weather.route) {
            WeatherScreen(navController = navController)
        }
        composable(Screen.Calendar.route) {
            CalendarScreen(navController = navController)
        }
        composable(Screen.CropHistory.route) {
            CropHistoryScreen(navController = navController)
        }
        composable(Screen.Settings.route) {
            SettingsScreen(navController = navController)
        }
        composable(Screen.AiAssistant.route) {
            AiAssistantScreen(navController = navController)
        }
        composable(Screen.SoilMonitoring.route) {
            SoilMonitoringScreen(navController = navController)
        }
        composable(Screen.Map.route) {
            MapScreen(navController = navController)
        }
        composable(Screen.Notifications.route) {
            NotificationsScreen(navController = navController)
        }
        composable(Screen.VoiceCommand.route) {
            VoiceCommandScreen(navController = navController)
        }
        composable(Screen.MicrophoneAccess.route) {
            MicrophoneAccessScreen(navController = navController)
        }
    }
}
