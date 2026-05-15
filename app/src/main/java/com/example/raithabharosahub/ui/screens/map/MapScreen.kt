package com.example.raithabharosahub.ui.screens.map

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapScreen(navController: NavHostController) {
    val cameraPositionState = rememberCameraPositionState()
    
    Scaffold { innerPadding ->
        Box(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
                uiSettings = MapUiSettings(zoomControlsEnabled = false, myLocationButtonEnabled = false)
            )

            // Top Bar Overlay
            Surface(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .height(64.dp),
                shape = MaterialTheme.shapes.large,
                color = Color.White.copy(alpha = 0.9f),
                shadowElevation = 4.dp
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = MaterialTheme.colorScheme.primary)
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(text = "Farm Location", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                }
            }

            // Weather Overlay Card
            Surface(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 32.dp, start = 16.dp, end = 16.dp)
                    .fillMaxWidth(),
                shape = MaterialTheme.shapes.extraLarge,
                color = Color.White,
                shadowElevation = 8.dp
            ) {
                Row(modifier = Modifier.padding(24.dp), verticalAlignment = Alignment.CenterVertically) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = "Dharwad, Karnataka", fontSize = 14.sp, color = Color.Gray, fontWeight = FontWeight.Medium)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = "Partly Cloudy • 28°C", fontSize = 18.sp, fontWeight = FontWeight.ExtraBold, color = Color.Black)
                    }
                    FloatingActionButton(
                        onClick = { /* TODO: Center on location */ },
                        containerColor = MaterialTheme.colorScheme.primary,
                        shape = CircleShape,
                        elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 2.dp)
                    ) {
                        Icon(Icons.Default.MyLocation, contentDescription = "My Location", tint = Color.White)
                    }
                }
            }
        }
    }
}
