package com.example.raithabharosahub.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.raithabharosahub.data.local.AppDatabase
import com.example.raithabharosahub.ui.CalendarViewModel
import com.example.raithabharosahub.ui.FarmerProfileScreen
import com.example.raithabharosahub.ui.HomeScreen
import com.example.raithabharosahub.ui.HomeViewModel
import com.example.raithabharosahub.ui.ProfileViewModel
import com.example.raithabharosahub.ui.screens.splash.SplashScreen
import com.example.raithabharosahub.ui.screens.onboarding.Onboarding1Screen
import com.example.raithabharosahub.ui.screens.onboarding.Onboarding2Screen
import com.example.raithabharosahub.ui.screens.language.LanguageSelectionScreen
import com.example.raithabharosahub.ui.screens.registration.RegistrationScreen
import com.example.raithabharosahub.ui.screens.location.LocationAccessScreen
import com.example.raithabharosahub.ui.screens.weather.WeatherScreen
import com.example.raithabharosahub.ui.screens.calendar.CalendarScreen
import com.example.raithabharosahub.ui.screens.history.CropHistoryScreen
import com.example.raithabharosahub.ui.screens.settings.SettingsScreen
import com.example.raithabharosahub.ui.screens.soil.SoilMonitoringScreen
import com.example.raithabharosahub.ui.screens.map.MapScreen
import com.example.raithabharosahub.ui.screens.notifications.NotificationsScreen

@Composable
fun NavGraph(navController: NavHostController) {
    val context = LocalContext.current
    val database = AppDatabase.getDatabase(context)

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
            val homeViewModel: HomeViewModel = viewModel()
            HomeScreen(navController = navController, viewModel = homeViewModel)
        }
        composable(Screen.Weather.route) {
            WeatherScreen(navController = navController)
        }
        composable(Screen.Calendar.route) {
            val calendarViewModel: CalendarViewModel = viewModel(
                factory = object : ViewModelProvider.Factory {
                    @Suppress("UNCHECKED_CAST")
                    override fun <T : ViewModel> create(modelClass: Class<T>): T {
                        return CalendarViewModel(database.activityDao()) as T
                    }
                }
            )
            CalendarScreen(navController = navController, viewModel = calendarViewModel)
        }
        composable(Screen.CropHistory.route) {
            CropHistoryScreen(navController = navController)
        }
        composable(Screen.Settings.route) {
            SettingsScreen(navController = navController)
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
        composable(Screen.Profile.route) {
            val profileViewModel: ProfileViewModel = viewModel()
            FarmerProfileScreen(navController = navController, viewModel = profileViewModel)
        }
        composable(Screen.ActionPlan.route) {
            // Placeholder for 7-day Krishi Action Plan
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("7-Day Krishi Action Plan (Coming Soon)")
            }
        }
    }
}
