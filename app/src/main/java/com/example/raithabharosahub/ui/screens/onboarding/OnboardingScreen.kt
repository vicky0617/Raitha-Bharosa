package com.example.raithabharosahub.ui.screens.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Agriculture
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.SmartToy
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.compose.ui.res.stringResource
import com.example.raithabharosahub.R
import com.example.raithabharosahub.ui.navigation.Screen

@Composable
fun Onboarding1Screen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            TextButton(onClick = { navController.navigate(Screen.LanguageSelection.route) }) {
                Text(stringResource(id = R.string.skip))
            }
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            // Illustration Card
            Card(
                modifier = Modifier
                    .size(280.dp),
                shape = MaterialTheme.shapes.extraLarge,
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
            ) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Default.Agriculture,
                        contentDescription = null,
                        modifier = Modifier.size(120.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "Modern Farming Assistant",
                fontSize = 28.sp,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(20.dp))

            val features = listOf(
                "Real-time weather updates",
                "Soil monitoring",
                "Sowing recommendations",
                "AI farming assistant"
            )

            Column(horizontalAlignment = Alignment.Start) {
                features.forEach { feature ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(vertical = 6.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = feature,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.DarkGray
                        )
                    }
                }
            }
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Row(horizontalArrangement = Arrangement.Center) {
                // Pagination Dots
                Box(modifier = Modifier.size(10.dp).background(MaterialTheme.colorScheme.primary, MaterialTheme.shapes.extraLarge))
                Spacer(modifier = Modifier.width(8.dp))
                Box(modifier = Modifier.size(10.dp).background(Color.LightGray, MaterialTheme.shapes.extraLarge))
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { navController.navigate(Screen.Onboarding2.route) },
                modifier = Modifier.fillMaxWidth().height(56.dp)
            ) {
                Text(stringResource(id = R.string.next))
            }
        }
    }
}

@Composable
fun Onboarding2Screen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            TextButton(onClick = { navController.navigate(Screen.LanguageSelection.route) }) {
                Text(stringResource(id = R.string.skip))
            }
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            // Illustration Card
            Card(
                modifier = Modifier
                    .size(280.dp),
                shape = MaterialTheme.shapes.extraLarge,
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
            ) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Default.SmartToy,
                        contentDescription = null,
                        modifier = Modifier.size(120.dp),
                        tint = MaterialTheme.colorScheme.secondary
                    )
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "Empowering Farmers",
                fontSize = 28.sp,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.secondary
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Make better decisions, optimize resources and get valuable farming insights with our AI assistant.",
                fontSize = 18.sp,
                lineHeight = 26.sp,
                textAlign = TextAlign.Center,
                color = Color.DarkGray,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Row(horizontalArrangement = Arrangement.Center) {
                // Pagination Dots
                Box(modifier = Modifier.size(10.dp).background(Color.LightGray, MaterialTheme.shapes.extraLarge))
                Spacer(modifier = Modifier.width(8.dp))
                Box(modifier = Modifier.size(10.dp).background(MaterialTheme.colorScheme.primary, MaterialTheme.shapes.extraLarge))
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { navController.navigate(Screen.LanguageSelection.route) },
                modifier = Modifier.fillMaxWidth().height(56.dp)
            ) {
                Text("Get Started")
            }
        }
    }
}
