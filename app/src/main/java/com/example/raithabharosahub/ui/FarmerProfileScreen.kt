package com.example.raithabharosahub.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FarmerProfileScreen(navController: NavController, viewModel: ProfileViewModel) {
    val profileState by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Farmer Profile", fontWeight = FontWeight.Bold, color = Color(0xFF1E4620)) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBackIosNew, contentDescription = "Back", tint = Color(0xFF1E4620))
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFFBFDFB))
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Profile Picture Circle Placeholder
            Surface(
                modifier = Modifier.size(100.dp),
                shape = CircleShape,
                color = Color(0xFFE8F5E9)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = null,
                        modifier = Modifier.size(70.dp),
                        tint = Color(0xFF4A7C59)
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Dynamic Input Fields (Editable state dependent)
            OutlinedTextField(
                value = profileState.farmerName,
                onValueChange = { viewModel.updateName(it) },
                label = { Text("Farmer Name") },
                enabled = profileState.isEditing,
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = Color(0xFF1E4620))
            )

            OutlinedTextField(
                value = profileState.primaryCrop,
                onValueChange = { viewModel.updateCrop(it) },
                label = { Text("Primary Crop") },
                enabled = profileState.isEditing,
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = Color(0xFF1E4620))
            )

            OutlinedTextField(
                value = profileState.location,
                onValueChange = { viewModel.updateLocation(it) },
                label = { Text("Farm Location") },
                enabled = profileState.isEditing,
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = Color(0xFF1E4620))
            )

            OutlinedTextField(
                value = profileState.phoneNumber,
                onValueChange = { viewModel.updatePhone(it) },
                label = { Text("Phone Number") },
                enabled = profileState.isEditing,
                modifier = Modifier.fillMaxWidth().padding(bottom = 32.dp),
                colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = Color(0xFF1E4620))
            )

            // Dynamic Action Button (Changes from Edit to Save)
            Button(
                onClick = { viewModel.toggleEditMode() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (profileState.isEditing) Color(0xFF4CAF50) else Color(0xFF1E4620)
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    text = if (profileState.isEditing) "Save Changes" else "Edit Details",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
