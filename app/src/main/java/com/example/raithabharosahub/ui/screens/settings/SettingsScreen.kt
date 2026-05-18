package com.example.raithabharosahub.ui.screens.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.raithabharosahub.ui.components.BottomNavigationBar
import com.example.raithabharosahub.ui.navigation.Screen
import com.example.raithabharosahub.viewmodel.SettingsViewModel

@Composable
fun SettingsScreen(navController: NavHostController, viewModel: SettingsViewModel = viewModel()) {
    LaunchedEffect(Unit) {
        viewModel.logoutEvent.collect {
            navController.navigate(Screen.Splash.route) {
                popUpTo(0) { inclusive = true }
            }
        }
    }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Screen Title
            Text(
                text = "Settings",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1E4620), // Fixed the color code from user input
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp)
            )

            // 1. Farmer Profile Card (CLICKABLE)
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
                    .clickable { navController.navigate(Screen.Profile.route) },
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF7FBF7))
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "Profile",
                        modifier = Modifier.size(48.dp),
                        tint = Color(0xFF4A7C59)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(text = "Farmer Profile", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                        Text(text = "View and edit details", color = Color.Gray, fontSize = 14.sp)
                    }
                }
            }

            // Options List Container
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column {
                    // App Language Option
                    SettingsItem(
                        icon = Icons.Default.Language,
                        title = "App Language",
                        subtitle = "English / ಕನ್ನಡ",
                        onClick = {
                            navController.navigate(Screen.LanguageSelection.route)
                        }
                    )
                    
                    HorizontalDivider(color = Color(0xFFEEEEEE))

                    // 2. Notifications Option
                    SettingsItem(
                        icon = Icons.Default.Notifications,
                        title = "Notifications",
                        subtitle = "Alerts & Reminders",
                        onClick = {
                            navController.navigate(Screen.Notifications.route)
                        }
                    )
                    
                    HorizontalDivider(color = Color(0xFFEEEEEE))

                    // 3. Help & Support Option
                    SettingsItem(
                        icon = Icons.Default.Info,
                        title = "Help & Support",
                        subtitle = "FAQs, Contact Us",
                        onClick = { /* navController.navigate("help_route") */ }
                    )
                    
                    HorizontalDivider(color = Color(0xFFEEEEEE))

                    // About App Option
                    SettingsItem(
                        icon = Icons.Default.Build,
                        title = "About App",
                        subtitle = "Version 1.0.0",
                        onClick = { /* Handle About click if needed */ }
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // Log Out Button
            Button(
                onClick = { viewModel.logout() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFDE8E8)),
                shape = RoundedCornerShape(24.dp)
            ) {
                Text(text = "Log Out", color = Color.Red, fontWeight = FontWeight.Bold)
            }
        }
    }
}

// Reusable component for the settings list items
@Composable
fun SettingsItem(
    icon: ImageVector,
    title: String,
    subtitle: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = icon, contentDescription = title, tint = Color(0xFF4A7C59), modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = title, fontWeight = FontWeight.Medium, fontSize = 16.sp)
            Text(text = subtitle, color = Color.Gray, fontSize = 12.sp)
        }
        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos, contentDescription = "Go", tint = Color.LightGray, modifier = Modifier.size(16.dp))
    }
}
