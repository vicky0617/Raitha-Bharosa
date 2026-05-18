package com.example.raithabharosahub.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.raithabharosahub.ui.components.BottomNavigationBar
import com.example.raithabharosahub.ui.navigation.Screen

@Composable
fun HomeScreen(navController: NavHostController, viewModel: HomeViewModel) {
    val state by viewModel.uiState.collectAsState()

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { paddingValues ->
        state?.let { data ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(Color(0xFFFBFDFB))
                    .padding(16.dp)
            ) {
                Spacer(modifier = Modifier.height(8.dp))

                // 1. Sowing Index Top Header Banner (Dynamic Colors based on brief)
                val bannerColor = if (data.isGoCondition) Color(0xFF1E4620) else Color(0xFFB00020)
                val subText = if (data.isGoCondition) "Excellent conditions for sowing today!" else "Wait! Conditions unfavorable."

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(containerColor = bannerColor)
                ) {
                    Row(
                        modifier = Modifier.padding(24.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Circular Progress Display
                        Box(contentAlignment = Alignment.Center) {
                            CircularProgressIndicator(
                                progress = { data.sowingIndex / 100f },
                                modifier = Modifier.size(72.dp),
                                color = Color.White,
                                strokeWidth = 6.dp,
                                trackColor = Color.White.copy(alpha = 0.2f),
                            )
                            Text(
                                text = "${data.sowingIndex}%",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp
                            )
                        }
                        Spacer(modifier = Modifier.width(20.dp))
                        Column {
                            Text(text = "Sowing Index", color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                            Text(text = subText, color = Color.White.copy(alpha = 0.85f), fontSize = 14.sp)
                        }
                    }
                }

                Text(
                    text = "Farm Monitoring",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1E4620),
                    modifier = Modifier.padding(vertical = 16.dp)
                )

                // 2. Monitoring Grid Items (Soil, Temp, Humidity, Rainfall)
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.height(220.dp)
                ) {
                    item { MonitoringCard("Soil Moisture", "${data.soilMoisture}%", Icons.Default.WaterDrop) }
                    item { MonitoringCard("Temp", "${data.temperature}°C", Icons.Default.DeviceThermostat) }
                    item { MonitoringCard("Humidity", "${data.humidity}%", Icons.Default.Cloud) }
                    item { MonitoringCard("Rainfall", "${data.rainfall}mm", Icons.Default.Umbrella) }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // 3. Expert Advice Section Box
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF2F5F2))
                ) {
                    Column(modifier = Modifier.padding(18.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(imageVector = Icons.Default.Lightbulb, contentDescription = "Advice", tint = Color(0xFF4A7C59))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = "Expert Advice", color = Color(0xFF4A7C59), fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = data.expertAdvice, color = Color.DarkGray, fontSize = 14.sp, lineHeight = 20.sp)
                        
                        Spacer(modifier = Modifier.height(16.dp))

                        // VIEW FULL GUIDE ACTION BUTTON (WIRED TO NAVIGATION)
                        Button(
                            onClick = { 
                                // Directs worker instantly to the 7-day Krishi Action Plan screen
                                navController.navigate(Screen.ActionPlan.route) 
                            },
                            modifier = Modifier.fillMaxWidth().height(48.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Text(text = "View Full Guide", color = Color.White, fontWeight = FontWeight.SemiBold)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MonitoringCard(title: String, value: String, icon: ImageVector) {
    Card(
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Icon(imageVector = icon, contentDescription = title, tint = Color(0xFF1E4620), modifier = Modifier.size(24.dp))
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = title, color = Color.Gray, fontSize = 14.sp)
            Text(text = value, fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color.Black)
        }
    }
}
