package com.example.raithabharosahub.ui.screens.location

import android.Manifest
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState

import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.raithabharosahub.viewmodel.LocationViewModel
import com.example.raithabharosahub.viewmodel.LocationState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun LocationAccessScreen(navController: NavHostController, viewModel: LocationViewModel = viewModel()) {
    val locationPermissionsState = rememberMultiplePermissionsState(
        listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    )
    val locationState by viewModel.locationState.collectAsState()

    LaunchedEffect(key1 = locationPermissionsState.allPermissionsGranted) {
        if (locationPermissionsState.allPermissionsGranted) {
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
            CircularProgressIndicator()
            Spacer(modifier = Modifier.height(16.dp))
            Text("Fetching your location...")
        } else {
            // Location Illustration Placeholder
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .background(MaterialTheme.colorScheme.primaryContainer, shape = MaterialTheme.shapes.extraLarge),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = null,
                    modifier = Modifier.size(100.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.height(48.dp))

            Text(
                text = "Enable Location Services",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "We need your location to provide accurate weather updates and soil analysis for your specific area.",
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                color = Color.Gray,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            if (locationState is LocationState.Error) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = (locationState as LocationState.Error).message,
                    color = MaterialTheme.colorScheme.error
                )
            }

            Spacer(modifier = Modifier.height(64.dp))

            Button(
                onClick = {
                    locationPermissionsState.launchMultiplePermissionRequest()
                },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                Text("Allow Location Access")
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(onClick = { navController.navigate(Screen.Dashboard.route) }) {
                Text("Not Now", color = Color.Gray)
            }
        }
    }
}
