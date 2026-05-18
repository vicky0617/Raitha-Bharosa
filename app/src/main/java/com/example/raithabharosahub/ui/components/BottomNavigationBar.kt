package com.example.raithabharosahub.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.raithabharosahub.ui.navigation.Screen

sealed class BottomNavItem(val title: String, val icon: ImageVector, val route: String) {
    object Home : BottomNavItem("Home", Icons.Default.Home, Screen.Dashboard.route)
    object Weather : BottomNavItem("Weather", Icons.Default.Cloud, Screen.Weather.route)
    object Calendar : BottomNavItem("Calendar", Icons.Default.DateRange, Screen.Calendar.route)
    object History : BottomNavItem("History", Icons.Default.History, Screen.CropHistory.route)
    object More : BottomNavItem("More", Icons.Default.Settings, Screen.Settings.route)
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Weather,
        BottomNavItem.Calendar,
        BottomNavItem.History,
        BottomNavItem.More
    )
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 12.dp),
        shape = MaterialTheme.shapes.extraLarge,
        color = Color.White,
        shadowElevation = 8.dp
    ) {
        NavigationBar(
            containerColor = Color.White,
            tonalElevation = 0.dp,
            modifier = Modifier.height(72.dp)
        ) {
            val navBackStackEntry = navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry.value?.destination?.route

            items.forEach { item ->
                val isSelected = currentRoute == item.route
                NavigationBarItem(
                    icon = { 
                        Icon(
                            imageVector = item.icon, 
                            contentDescription = item.title,
                            modifier = Modifier.size(26.dp)
                        ) 
                    },
                    label = { 
                        Text(
                            text = item.title,
                            fontSize = 11.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                        ) 
                    },
                    selected = isSelected,
                    onClick = {
                        navController.navigate(item.route) {
                            // popUpTo(navController.graph.findStartDestination().id) {
                            //     saveState = true
                            // }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.primary,
                        selectedTextColor = MaterialTheme.colorScheme.primary,
                        unselectedIconColor = Color.Gray.copy(alpha = 0.6f),
                        unselectedTextColor = Color.Gray.copy(alpha = 0.6f),
                        indicatorColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f)
                    )
                )
            }
        }
    }
}
