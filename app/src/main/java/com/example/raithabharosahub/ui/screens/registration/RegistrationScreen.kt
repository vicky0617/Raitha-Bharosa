package com.example.raithabharosahub.ui.screens.registration

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.raithabharosahub.ui.navigation.Screen

import com.example.raithabharosahub.viewmodel.RegistrationViewModel
import com.example.raithabharosahub.viewmodel.RegistrationState

@Composable
fun RegistrationScreen(navController: NavHostController, viewModel: RegistrationViewModel = viewModel()) {
    var fullName by remember { mutableStateOf("") }
    var mobileNumber by remember { mutableStateOf("") }
    var village by remember { mutableStateOf("") }
    var district by remember { mutableStateOf("") }
    var primaryCrop by remember { mutableStateOf("") }
    
    val registrationState by viewModel.registrationState.collectAsState()

    LaunchedEffect(registrationState) {
        if (registrationState is RegistrationState.Success) {
            navController.navigate(Screen.LocationAccess.route) {
                popUpTo(Screen.Registration.route) { inclusive = true }
            }
        }
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Farmer Registration",
                fontSize = 32.sp,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Please enter your details to get started",
                fontSize = 16.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(40.dp))

            if (registrationState is RegistrationState.Error) {
                Card(
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer),
                    modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp)
                ) {
                    Text(
                        text = (registrationState as RegistrationState.Error).message,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(16.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }

            // Profile Avatar
            Surface(
                modifier = Modifier.size(120.dp),
                shape = CircleShape,
                color = MaterialTheme.colorScheme.primaryContainer,
                shadowElevation = 4.dp
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                        modifier = Modifier.size(60.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            ModernTextField(
                value = fullName,
                onValueChange = { fullName = it; viewModel.clearError() },
                label = "Full Name",
                icon = Icons.Default.Person,
                enabled = registrationState !is RegistrationState.Loading
            )

            Spacer(modifier = Modifier.height(20.dp))

            ModernTextField(
                value = mobileNumber,
                onValueChange = { mobileNumber = it; viewModel.clearError() },
                label = "Mobile Number",
                icon = Icons.Default.Phone,
                enabled = registrationState !is RegistrationState.Loading
            )

            Spacer(modifier = Modifier.height(20.dp))

            ModernTextField(
                value = village,
                onValueChange = { village = it; viewModel.clearError() },
                label = "Village / Town",
                icon = Icons.Default.HomeWork,
                enabled = registrationState !is RegistrationState.Loading
            )

            Spacer(modifier = Modifier.height(20.dp))

            ModernTextField(
                value = district,
                onValueChange = { district = it; viewModel.clearError() },
                label = "District",
                icon = Icons.Default.LocationCity,
                enabled = registrationState !is RegistrationState.Loading
            )

            Spacer(modifier = Modifier.height(20.dp))

            ModernTextField(
                value = primaryCrop,
                onValueChange = { primaryCrop = it; viewModel.clearError() },
                label = "Primary Crop",
                icon = Icons.Default.Agriculture,
                enabled = registrationState !is RegistrationState.Loading
            )

            Spacer(modifier = Modifier.height(56.dp))

            if (registrationState is RegistrationState.Loading) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
            } else {
                Button(
                    onClick = {
                        viewModel.registerFarmer(fullName, mobileNumber, village, district, primaryCrop)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp),
                    shape = MaterialTheme.shapes.large,
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
                ) {
                    Text("Register & Continue", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun ModernTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    icon: ImageVector,
    enabled: Boolean
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        leadingIcon = { Icon(icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary) },
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        enabled = enabled,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = Color.LightGray.copy(alpha = 0.5f),
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White
        ),
        singleLine = true
    )
}
