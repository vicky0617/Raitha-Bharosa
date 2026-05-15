package com.example.raithabharosahub.ui.screens.assistant

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.SmartToy
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.MicOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.raithabharosahub.ui.components.BottomNavigationBar
import com.example.raithabharosahub.ui.navigation.Screen

import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.raithabharosahub.services.VoiceAssistantManager
import com.example.raithabharosahub.viewmodel.AssistantViewModel

import com.example.raithabharosahub.data.local.UserPreferences

@Composable
fun AiAssistantScreen(navController: NavHostController, viewModel: AssistantViewModel = viewModel()) {
    val context = LocalContext.current
    val userPreferences = remember { UserPreferences(context) }
    val language by userPreferences.languageFlow.collectAsState(initial = "en")
    
    val chatMessages by viewModel.chatMessages.collectAsState()
    val isListening by viewModel.isListening.collectAsState()
    var message by remember { mutableStateOf("") }

    val voiceManager = remember {
        VoiceAssistantManager(
            context = context,
            onResult = { result ->
                viewModel.sendMessage(result, language == "kn")
                viewModel.setListening(false)
            },
            onError = { _ ->
                viewModel.setListening(false)
            }
        )
    }

    DisposableEffect(Unit) {
        onDispose {
            voiceManager.destroy()
        }
    }

    LaunchedEffect(chatMessages) {
        val lastMsg = chatMessages.lastOrNull()
        if (lastMsg != null && !lastMsg.second) {
            voiceManager.speak(lastMsg.first, if (language == "kn") "kn-IN" else "en-US")
            
            // Execute navigation if command detected
            val lowerMsg = lastMsg.first.lowercase()
            when {
                lowerMsg.contains("weather") || lowerMsg.contains("ಹವಾಮಾನ") -> navController.navigate(Screen.Weather.route)
                lowerMsg.contains("calendar") || lowerMsg.contains("ಕ್ಯಾಲೆಂಡರ್") -> navController.navigate(Screen.Calendar.route)
                lowerMsg.contains("soil") || lowerMsg.contains("ಮಣ್ಣು") -> navController.navigate(Screen.SoilMonitoring.route)
                lowerMsg.contains("map") || lowerMsg.contains("ನಕ್ಷೆ") -> navController.navigate(Screen.Map.route)
            }
        }
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
            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(
                    modifier = Modifier.size(56.dp),
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(Icons.Default.SmartToy, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(32.dp))
                    }
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(text = "Krishi AI Assistant", fontSize = 22.sp, fontWeight = FontWeight.ExtraBold, color = MaterialTheme.colorScheme.primary)
                    Text(text = "Online • Ready to help", fontSize = 13.sp, color = MaterialTheme.colorScheme.secondary, fontWeight = FontWeight.Bold)
                }
            }
            Spacer(modifier = Modifier.height(28.dp))

            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(bottom = 20.dp)
            ) {
                items(chatMessages) { msg ->
                    ChatBubble(msg.first, msg.second)
                }
            }

            // Suggested Prompts
            if (chatMessages.size <= 2) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    val prompts = listOf("Weather Update", "Soil Status", "Next Sowing")
                    prompts.forEach { prompt ->
                        Surface(
                            modifier = Modifier.clickable { viewModel.sendMessage(prompt, language == "kn") },
                            shape = CircleShape,
                            color = Color.White,
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.2f))
                        ) {
                            Text(text = prompt, modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp), fontSize = 13.sp, fontWeight = FontWeight.Medium, color = MaterialTheme.colorScheme.primary)
                        }
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = message,
                    onValueChange = { message = it },
                    modifier = Modifier.weight(1f),
                    placeholder = { Text("Ask anything...") },
                    shape = CircleShape,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = Color.LightGray.copy(alpha = 0.5f)
                    ),
                    trailingIcon = {
                        if (message.isNotBlank()) {
                            IconButton(onClick = {
                                viewModel.sendMessage(message, language == "kn")
                                message = ""
                            }) {
                                Icon(Icons.AutoMirrored.Filled.Send, contentDescription = "Send", tint = MaterialTheme.colorScheme.primary)
                            }
                        }
                    }
                )
                Spacer(modifier = Modifier.width(12.dp))
                FloatingActionButton(
                    onClick = {
                        if (isListening) {
                            voiceManager.stopListening()
                            viewModel.setListening(false)
                        } else {
                            voiceManager.startListening(if (language == "kn") "kn-IN" else "en-US")
                            viewModel.setListening(true)
                        }
                    },
                    modifier = Modifier.size(56.dp),
                    containerColor = if (isListening) Color.Red else MaterialTheme.colorScheme.primary,
                    shape = CircleShape,
                    elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 4.dp)
                ) {
                    Icon(
                        imageVector = if (isListening) Icons.Default.MicOff else Icons.Default.Mic,
                        contentDescription = "Voice",
                        tint = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun ChatBubble(message: String, isUser: Boolean) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = if (isUser) Alignment.CenterEnd else Alignment.CenterStart
    ) {
        Surface(
            color = if (isUser) MaterialTheme.colorScheme.primary else Color.White,
            shape = RoundedCornerShape(
                topStart = 20.dp,
                topEnd = 20.dp,
                bottomStart = if (isUser) 20.dp else 0.dp,
                bottomEnd = if (isUser) 0.dp else 20.dp
            ),
            shadowElevation = if (isUser) 4.dp else 1.dp
        ) {
            Text(
                text = message,
                modifier = Modifier.padding(16.dp),
                color = if (isUser) Color.White else Color.Black,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                lineHeight = 22.sp
            )
        }
    }
}
