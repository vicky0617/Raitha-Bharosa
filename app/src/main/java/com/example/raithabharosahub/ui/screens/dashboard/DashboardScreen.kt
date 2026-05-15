package com.example.raithabharosahub.ui.screens.dashboard

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.raithabharosahub.ui.components.BottomNavigationBar

import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.raithabharosahub.viewmodel.FarmerViewModel
import com.example.raithabharosahub.viewmodel.WeatherViewModel
import com.example.raithabharosahub.viewmodel.WeatherState

import com.example.raithabharosahub.ui.navigation.Screen

@Composable
fun DashboardScreen(
    navController: NavHostController,
    farmerViewModel: FarmerViewModel = viewModel(),
    weatherViewModel: WeatherViewModel = viewModel()
) {
    val farmerProfile by farmerViewModel.farmerProfile.collectAsState(initial = null)
    val weatherState by weatherViewModel.weatherState.collectAsState()

    LaunchedEffect(Unit) {
        weatherViewModel.fetchWeather(15.3647, 75.1240) // Default to Dharwad
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            HeaderSection(farmerProfile?.fullName ?: "Farmer", navController)
            Spacer(modifier = Modifier.height(28.dp))
            
            // Image Banner
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp),
                shape = MaterialTheme.shapes.extraLarge,
                color = MaterialTheme.colorScheme.secondaryContainer
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Default.Agriculture,
                        contentDescription = null,
                        modifier = Modifier.size(80.dp),
                        tint = MaterialTheme.colorScheme.secondary
                    )
                    Text(
                        text = "Smart Farming Insights",
                        modifier = Modifier.align(Alignment.BottomStart).padding(20.dp),
                        fontWeight = FontWeight.Bold,
                        color = Color.DarkGray
                    )
                }
            }

            Spacer(modifier = Modifier.height(28.dp))
            
            val weatherData = (weatherState as? WeatherState.Success)?.data
            val temp = weatherData?.main?.temp ?: 28.0
            val humidity = weatherData?.main?.humidity ?: 45
            val rain = weatherData?.rain?.oneHour ?: 0.0
            
            val isGoodForSowing = rain < 1.0 && humidity > 30 
            val sowingIndex = if (isGoodForSowing) 0.85f else 0.45f
            val sowingMessage = if (isGoodForSowing) "Excellent conditions for sowing today!" else "Wait for better conditions."

            SowingIndexSection(sowingIndex, sowingMessage)
            Spacer(modifier = Modifier.height(28.dp))
            
            SoilMetricsSection(humidity, temp, rain)
            Spacer(modifier = Modifier.height(28.dp))
            
            RecommendationSection(isGoodForSowing, farmerProfile?.primaryCrop ?: "Groundnut")
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun HeaderSection(name: String, navController: NavHostController) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "Welcome back,",
                fontSize = 16.sp,
                color = Color.Gray,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = name,
                fontSize = 28.sp,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.primary
            )
        }
        Surface(
            modifier = Modifier.size(56.dp),
            shape = MaterialTheme.shapes.large,
            color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f),
            onClick = { navController.navigate(Screen.Notifications.route) }
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    Icons.Default.Notifications,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(28.dp)
                )
            }
        }
    }
}

@Composable
fun SowingIndexSection(index: Float, message: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.extraLarge,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Row(
            modifier = Modifier.padding(24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.size(90.dp)) {
                CircularProgressIndicator(
                    progress = index,
                    modifier = Modifier.fillMaxSize(),
                    strokeWidth = 10.dp,
                    color = Color.White,
                    trackColor = Color.White.copy(alpha = 0.2f)
                )
                Text(
                    text = "${(index * 100).toInt()}%",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 22.sp,
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.width(24.dp))
            Column {
                Text(
                    text = "Sowing Index",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = message,
                    fontSize = 15.sp,
                    color = Color.White.copy(alpha = 0.9f),
                    lineHeight = 20.sp
                )
            }
        }
    }
}

@Composable
fun SoilMetricsSection(humidity: Int, temp: Double, rain: Double) {
    Column {
        Text(
            text = "Farm Monitoring",
            fontSize = 20.sp,
            fontWeight = FontWeight.ExtraBold,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            SoilMetricCard("Soil Moisture", "32%", Icons.Default.WaterDrop, Modifier.weight(1f))
            SoilMetricCard("Temp", "${temp.toInt()}°C", Icons.Default.Thermostat, Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            SoilMetricCard("Humidity", "$humidity%", Icons.Default.Cloud, Modifier.weight(1f))
            SoilMetricCard("Rainfall", "${rain}mm", Icons.Default.Umbrella, Modifier.weight(1f))
        }
    }
}

@Composable
fun SoilMetricCard(label: String, value: String, icon: ImageVector, modifier: Modifier) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.large,
        color = Color.White,
        shadowElevation = 2.dp
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Surface(
                modifier = Modifier.size(44.dp),
                shape = MaterialTheme.shapes.medium,
                color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = label, fontSize = 14.sp, color = Color.Gray, fontWeight = FontWeight.Medium)
            Text(text = value, fontSize = 20.sp, fontWeight = FontWeight.ExtraBold, color = Color.Black)
        }
    }
}

@Composable
fun RecommendationSection(isGood: Boolean, crop: String) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.extraLarge,
        color = if (isGood) MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.5f) else MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.5f),
        border = BorderStroke(1.dp, if (isGood) MaterialTheme.colorScheme.secondary.copy(alpha = 0.2f) else MaterialTheme.colorScheme.error.copy(alpha = 0.2f))
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.TipsAndUpdates,
                    contentDescription = null,
                    tint = if (isGood) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.error
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "Expert Advice",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (isGood) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.error
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            val text = if (isGood) {
                "Perfect conditions for sowing $crop. Low rain probability and optimal moisture detected."
            } else {
                "Unfavorable conditions for $crop. High rain probability. Better to wait for 2-3 days."
            }
            Text(
                text = text,
                fontSize = 15.sp,
                color = Color.DarkGray,
                lineHeight = 22.sp
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = { /* TODO */ },
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.large,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isGood) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.error
                )
            ) {
                Text("View Full Guide", fontWeight = FontWeight.Bold)
            }
        }
    }
}
