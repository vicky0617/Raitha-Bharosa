package com.example.raithabharosahub.ui.screens.weather

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.raithabharosahub.ui.components.BottomNavigationBar

import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.raithabharosahub.viewmodel.WeatherViewModel
import com.example.raithabharosahub.viewmodel.WeatherState
import com.example.raithabharosahub.data.remote.WeatherResponse
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.text.style.TextAlign
import androidx.compose.material.icons.filled.LocationOn

@Composable
fun WeatherScreen(navController: NavHostController, viewModel: WeatherViewModel = viewModel()) {
    val weatherState by viewModel.weatherState.collectAsState()
    
    LaunchedEffect(Unit) {
        viewModel.fetchWeather(15.3647, 75.1240) // Dharwad coordinates
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
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Weather Forecast",
                fontSize = 28.sp,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(24.dp))

            when (val state = weatherState) {
                is WeatherState.Loading -> {
                    Box(modifier = Modifier.fillMaxWidth().height(250.dp), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                    }
                }
                is WeatherState.Success -> {
                    CurrentWeatherCard(state.data)
                }
                is WeatherState.Error -> {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer)
                    ) {
                        Column(modifier = Modifier.padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = state.message, color = MaterialTheme.colorScheme.error, textAlign = TextAlign.Center)
                            Spacer(modifier = Modifier.height(16.dp))
                            Button(onClick = { viewModel.fetchWeather(15.3647, 75.1240) }) {
                                Text("Retry")
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Next 7 Days",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(16.dp))

            ForecastList()
        }
    }
}

@Composable
fun CurrentWeatherCard(data: WeatherResponse) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.extraLarge,
        color = MaterialTheme.colorScheme.primary,
        shadowElevation = 12.dp
    ) {
        Column(
            modifier = Modifier
                .background(
                    brush = androidx.compose.ui.graphics.Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.primary,
                            MaterialTheme.colorScheme.secondary
                        )
                    )
                )
                .padding(28.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.LocationOn, contentDescription = null, tint = Color.White.copy(alpha = 0.8f), modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text(text = data.name, color = Color.White, fontWeight = FontWeight.Medium)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Icon(
                imageVector = Icons.Default.WbSunny,
                contentDescription = null,
                modifier = Modifier.size(100.dp),
                tint = Color(0xFFFFEB3B)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "${data.main.temp.toInt()}°C", fontSize = 64.sp, fontWeight = FontWeight.ExtraBold, color = Color.White)
            Text(text = data.weather.firstOrNull()?.main ?: "Clear", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.White.copy(alpha = 0.9f))
            
            Spacer(modifier = Modifier.height(32.dp))
            
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White.copy(alpha = 0.15f), MaterialTheme.shapes.large)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                WeatherMetricItem("Humidity", "${data.main.humidity}%")
                WeatherMetricItem("Wind", "${data.wind?.speed ?: 0.0} km/h")
                WeatherMetricItem("Rain", "${data.rain?.oneHour ?: 0.0} mm")
            }
        }
    }
}

@Composable
fun WeatherMetricItem(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = value, fontWeight = FontWeight.ExtraBold, color = Color.White, fontSize = 16.sp)
        Text(text = label, fontSize = 12.sp, color = Color.White.copy(alpha = 0.7f), fontWeight = FontWeight.Medium)
    }
}

@Composable
fun ForecastList() {
    val forecast = listOf(
        Pair("Mon", "31° / 24°"),
        Pair("Tue", "30° / 23°"),
        Pair("Wed", "28° / 22°"),
        Pair("Thu", "29° / 23°"),
        Pair("Fri", "32° / 25°"),
        Pair("Sat", "33° / 26°"),
        Pair("Sun", "31° / 24°")
    )

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(bottom = 24.dp)
    ) {
        items(forecast) { item ->
            ForecastItem(item.first, item.second)
        }
    }
}

@Composable
fun ForecastItem(day: String, temp: String) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        color = Color.White,
        shadowElevation = 1.dp
    ) {
        Row(
            modifier = Modifier.padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = day, fontWeight = FontWeight.Bold, fontSize = 18.sp, modifier = Modifier.width(60.dp), color = Color.Black)
            Icon(imageVector = Icons.Default.Cloud, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(28.dp))
            Text(text = temp, fontWeight = FontWeight.ExtraBold, fontSize = 16.sp, color = MaterialTheme.colorScheme.primary)
        }
    }
}
