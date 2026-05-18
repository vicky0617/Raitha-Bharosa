package com.example.raithabharosahub.ui.screens.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Agriculture
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.TipsAndUpdates
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.raithabharosahub.R
import com.example.raithabharosahub.ui.navigation.Screen

@Composable
fun Onboarding1Screen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            TextButton(onClick = { navController.navigate(Screen.LanguageSelection.route) }) {
                Text(stringResource(id = R.string.skip), color = Color.Gray)
            }
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = stringResource(id = R.string.onboarding1_title),
                fontSize = 32.sp,
                lineHeight = 40.sp,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center,
                color = Color(0xFF1B5E20)
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = stringResource(id = R.string.onboarding1_kn_title),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color(0xFF4CAF50)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Illustration Card
            Card(
                modifier = Modifier.size(300.dp),
                shape = MaterialTheme.shapes.extraLarge,
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
            ) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Default.Agriculture,
                        contentDescription = null,
                        modifier = Modifier.size(150.dp),
                        tint = Color(0xFF4CAF50)
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            val features = listOf(
                R.string.feature_weather,
                R.string.feature_soil,
                R.string.feature_sowing,
                R.string.feature_assistant
            )

            Column(horizontalAlignment = Alignment.Start) {
                features.forEach { resId ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(vertical = 6.dp)
                    ) {
                        // Diamond Bullet
                        Surface(
                            modifier = Modifier.size(8.dp).rotate(45f),
                            color = Color(0xFFFFC107)
                        ) {}
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            text = stringResource(id = resId),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.DarkGray
                        )
                    }
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row {
                // Pagination Dots
                Box(modifier = Modifier.size(12.dp).background(Color(0xFF1B5E20), CircleShape))
                Spacer(modifier = Modifier.width(8.dp))
                Box(modifier = Modifier.size(12.dp).background(Color.LightGray, CircleShape))
            }

            FloatingActionButton(
                onClick = { navController.navigate(Screen.Onboarding2.route) },
                containerColor = Color(0xFF1B5E20),
                contentColor = Color.White,
                shape = CircleShape
            ) {
                Icon(Icons.Default.ChevronRight, contentDescription = "Next")
            }
        }
    }
}

@Composable
fun Onboarding2Screen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            TextButton(onClick = { navController.navigate(Screen.LanguageSelection.route) }) {
                Text(stringResource(id = R.string.skip), color = Color.Gray)
            }
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = stringResource(id = R.string.onboarding2_title),
                fontSize = 32.sp,
                lineHeight = 40.sp,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center,
                color = Color(0xFF1B5E20)
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = stringResource(id = R.string.onboarding2_kn_subtitle),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color(0xFF4CAF50)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Illustration Card
            Card(
                modifier = Modifier.size(300.dp),
                shape = MaterialTheme.shapes.extraLarge,
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
            ) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Default.Agriculture,
                        contentDescription = null,
                        modifier = Modifier.size(180.dp),
                        tint = Color(0xFF4CAF50)
                    )
                    // Sun icon overlay feel
                    Box(modifier = Modifier.fillMaxSize().padding(24.dp), contentAlignment = Alignment.TopStart) {
                        Surface(
                            modifier = Modifier.size(60.dp),
                            shape = CircleShape,
                            color = Color(0xFFFFF9C4),
                            shadowElevation = 2.dp
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(Icons.Default.WbSunny, contentDescription = null, tint = Color(0xFFFFC107), modifier = Modifier.size(32.dp))
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            val features = listOf(
                R.string.feature_increase_yield,
                R.string.feature_save_resources,
                R.string.feature_reduce_risks
            )

            Column(horizontalAlignment = Alignment.Start) {
                features.forEach { resId ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(vertical = 6.dp)
                    ) {
                        Surface(
                            modifier = Modifier.size(8.dp).rotate(45f),
                            color = Color(0xFFFFC107)
                        ) {}
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            text = stringResource(id = resId),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.DarkGray
                        )
                    }
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row {
                Box(modifier = Modifier.size(12.dp).background(Color.LightGray, CircleShape))
                Spacer(modifier = Modifier.width(8.dp))
                Box(modifier = Modifier.size(12.dp).background(Color(0xFF1B5E20), CircleShape))
            }

            Button(
                onClick = { navController.navigate(Screen.LanguageSelection.route) },
                modifier = Modifier.height(56.dp).width(160.dp),
                shape = MaterialTheme.shapes.large,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1B5E20))
            ) {
                Text(stringResource(id = R.string.get_started), fontWeight = FontWeight.Bold)
            }
        }
    }
}
