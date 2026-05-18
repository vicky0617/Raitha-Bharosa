package com.example.raithabharosahub.ui.screens.location

import android.Manifest
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.raithabharosahub.ui.navigation.Screen
import com.google.accompanist.permissions.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.raithabharosahub.viewmodel.LocationViewModel
import com.example.raithabharosahub.viewmodel.LocationState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun LocationAccessScreen(navController: NavHostController, viewModel: LocationViewModel = viewModel()) {
    // Track the status of the location permission
    val locationPermissionState = rememberPermissionState(
        permission = Manifest.permission.ACCESS_FINE_LOCATION
    )

    val locationState by viewModel.locationState.collectAsState()

    // Automatically navigate to dashboard if permission is already granted and location is fetched
    LaunchedEffect(key1 = locationPermissionState.status.isGranted) {
        if (locationPermissionState.status.isGranted) {
            viewModel.fetchCurrentLocation()
        }
    }

    LaunchedEffect(key1 = locationState) {
        if (locationState is LocationState.Success) {
            navController.navigate(Screen.Dashboard.route) {
                popUpTo(Screen.LocationAccess.route) { inclusive = true }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (locationState is LocationState.Loading) {
            CircularProgressIndicator(color = Color(0xFF1E4620))
            Spacer(modifier = Modifier.height(16.dp))
            Text("Fetching your location...")
        } else {
            // Location Pin Visual
            Surface(
                modifier = Modifier.size(160.dp),
                shape = RoundedCornerShape(32.dp),
                color = Color(0xFFE8F5E9) // Light green background matching your style
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "Location Pin",
                        modifier = Modifier.size(80.dp),
                        tint = Color(0xFF1E4620) // Deep agricultural green
                    )
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "Enable Location Services",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "We need your location to provide accurate weather updates and soil analysis for your specific area.",
                fontSize = 16.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                lineHeight = 22.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Dynamic Error Messaging
            if (!locationPermissionState.status.isGranted) {
                Text(
                    text = "Location permission is required",
                    color = Color(0xFFB00020), // Standard material design red
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            } else if (locationState is LocationState.Error) {
                Text(
                    text = (locationState as LocationState.Error).message,
                    color = Color(0xFFB00020),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            // "Allow Location Access" Button
            Button(
                onClick = {
                    // This forces the system dialog box to pop up on screen
                    locationPermissionState.launchPermissionRequest()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E4620)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Allow Location Access",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // "Not Now" Button
            TextButton(
                onClick = { 
                    navController.navigate(Screen.Dashboard.route)
                }
            ) {
                Text(
                    text = "Not Now",
                    color = Color.Gray,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}
