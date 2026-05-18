package com.example.raithabharosahub.ui.screens.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.raithabharosahub.data.KrishiActivity
import com.example.raithabharosahub.ui.CalendarViewModel
import com.example.raithabharosahub.ui.components.BottomNavigationBar

@Composable
fun CalendarScreen(navController: NavHostController, viewModel: CalendarViewModel) {
    val selectedDate by viewModel.selectedDate.collectAsState()
    val activities by viewModel.currentActivities.collectAsState()

    val daysOfWeek = listOf("15", "16", "17", "18", "19", "20", "21")
    val dayLabels = listOf("S", "M", "T", "W", "T", "F", "S")

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { viewModel.addNewMockActivity() },
                containerColor = Color(0xFF1E4620),
                contentColor = Color.White,
                shape = CircleShape
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Task")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFFBFDFB))
                .padding(16.dp)
        ) {
            Text(
                text = "Krishi Calendar",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1E4620)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Month Header Panel Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "<", fontSize = 20.sp, color = Color(0xFF1E4620), fontWeight = FontWeight.Bold)
                        Text(text = "May 2026", fontSize = 18.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF1E4620))
                        Text(text = ">", fontSize = 20.sp, color = Color(0xFF1E4620), fontWeight = FontWeight.Bold)
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // 1. WORKING DAY ROW SELECTION
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        daysOfWeek.forEachIndexed { index, day ->
                            val isSelected = selectedDate == day
                            
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .clickable { viewModel.selectDate(day) } // Updates active data instantly
                                    .padding(4.dp)
                            ) {
                                Text(text = dayLabels[index], color = Color.Gray, fontSize = 12.sp)
                                Spacer(modifier = Modifier.height(8.dp))
                                
                                Box(
                                    modifier = Modifier
                                        .size(36.dp)
                                        .background(
                                            color = if (isSelected) Color(0xFF1E4620) else Color.Transparent,
                                            shape = CircleShape
                                        ),
                                    contentAlignment = Alignment.Center
                                    ) {
                                    Text(
                                        text = day,
                                        color = if (isSelected) Color.White else Color.Black,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 14.sp
                                    )
                                }
                            }
                        }
                    }
                }
            }

            Text(
                text = "Activities for May $selectedDate",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1E4620),
                modifier = Modifier.padding(vertical = 16.dp)
            )

            // 2. DYNAMICALLY LOADED LIST CARDS
            if (activities.isEmpty()) {
                Box(modifier = Modifier.fillMaxWidth().weight(1f), contentAlignment = Alignment.Center) {
                    Text(text = "No activities planned for this day.", color = Color.Gray)
                }
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxWidth().weight(1f)
                ) {
                    items(activities) { activity ->
                        ActivityItemCard(activity)
                    }
                }
            }
        }
    }
}

@Composable
fun ActivityItemCard(activity: KrishiActivity) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = Color(0xFFF0F4F1),
                modifier = Modifier.size(44.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(imageVector = Icons.Default.CalendarToday, contentDescription = null, tint = Color(0xFF1E4620))
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = activity.title, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.Black)
                Text(text = "${activity.time} • ${activity.category}", color = Color.Gray, fontSize = 13.sp)
            }
        }
    }
}
