package com.example.raithabharosahub.ui.screens.soil

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.foundation.background
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.ui.graphics.vector.ImageVector

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SoilMonitoringScreen(navController: NavHostController) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Soil Health Analysis", fontWeight = FontWeight.ExtraBold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = MaterialTheme.colorScheme.primary)
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.Transparent)
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Nutrient Levels (NPK)", fontSize = 20.sp, fontWeight = FontWeight.ExtraBold, color = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.height(20.dp))

            NPKCard("Nitrogen (N)", 0.65f, "Optimal", Color(0xFF4CAF50))
            Spacer(modifier = Modifier.height(16.dp))
            NPKCard("Phosphorus (P)", 0.45f, "Low", Color(0xFFFFC107))
            Spacer(modifier = Modifier.height(16.dp))
            NPKCard("Potassium (K)", 0.80f, "High", Color(0xFF2196F3))

            Spacer(modifier = Modifier.height(32.dp))

            Text(text = "Moisture & Health", fontSize = 20.sp, fontWeight = FontWeight.ExtraBold, color = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.height(20.dp))

            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.extraLarge,
                color = Color.White,
                shadowElevation = 2.dp
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    HealthMetricRow("Soil Moisture", 0.32f, "32%", Color(0xFF03A9F4))
                    HorizontalDivider(modifier = Modifier.padding(vertical = 20.dp), color = Color.LightGray.copy(alpha = 0.3f))
                    HealthMetricRow("Overall Soil Quality", 0.85f, "Excellent", Color(0xFF8BC34A))
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(text = "Physical Parameters", fontSize = 20.sp, fontWeight = FontWeight.ExtraBold, color = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.height(20.dp))

            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.large,
                color = Color.White,
                shadowElevation = 1.dp
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    MetricRow("Soil pH Level", "6.5 (Neutral)")
                    MetricRow("Organic Matter", "2.5%")
                    MetricRow("EC (Conductivity)", "1.2 dS/m")
                    MetricRow("Soil Temperature", "24.5°C")
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun NPKCard(label: String, progress: Float, status: String, color: Color) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        color = Color.White,
        shadowElevation = 2.dp
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Text(text = label, fontWeight = FontWeight.Bold, fontSize = 17.sp, color = Color.Black)
                Surface(
                    color = color.copy(alpha = 0.1f),
                    shape = CircleShape
                ) {
                    Text(text = status, color = color, fontWeight = FontWeight.ExtraBold, fontSize = 13.sp, modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp))
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp)
                    .background(color.copy(alpha = 0.1f), CircleShape),
                color = color,
                strokeCap = StrokeCap.Round,
            )
        }
    }
}

@Composable
fun HealthMetricRow(label: String, progress: Float, value: String, color: Color) {
    Column {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text(text = label, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.Black)
            Text(text = value, fontWeight = FontWeight.ExtraBold, fontSize = 16.sp, color = color)
        }
        Spacer(modifier = Modifier.height(12.dp))
        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp),
            color = color,
            trackColor = color.copy(alpha = 0.1f),
            strokeCap = StrokeCap.Round
        )
    }
}

@Composable
fun MetricRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = label, color = Color.Gray, fontWeight = FontWeight.Medium, fontSize = 15.sp)
        Text(text = value, fontWeight = FontWeight.ExtraBold, color = Color.Black, fontSize = 15.sp)
    }
}
