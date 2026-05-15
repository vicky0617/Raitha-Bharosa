package com.example.raithabharosahub.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AssistantViewModel : ViewModel() {
    private val _chatMessages = MutableStateFlow<List<Pair<String, Boolean>>>(
        listOf(
            Pair("Hello! I am your AI Farming Assistant. How can I help you today?", false),
            Pair("You can ask me about weather, soil, or sowing recommendations.", false)
        )
    )
    val chatMessages: StateFlow<List<Pair<String, Boolean>>> = _chatMessages

    private val _isListening = MutableStateFlow(false)
    val isListening: StateFlow<Boolean> = _isListening

    fun sendMessage(message: String, isKannada: Boolean = false) {
        val currentList = _chatMessages.value.toMutableList()
        currentList.add(Pair(message, true))
        _chatMessages.value = currentList
        
        processCommand(message, isKannada)
    }

    private fun processCommand(command: String, isKannada: Boolean) {
        val lowerMsg = command.lowercase()
        val response = when {
            lowerMsg.contains("weather") || lowerMsg.contains("ಹವಾಮಾನ") -> {
                if (isKannada) "ಹವಾಮಾನ ಪರದೆಯನ್ನು ತೆರೆಯಲಾಗುತ್ತಿದೆ..." else "Opening Weather screen..."
            }
            lowerMsg.contains("calendar") || lowerMsg.contains("ಕ್ಯಾಲೆಂಡರ್") -> {
                if (isKannada) "ಕೃಷಿ ಕ್ಯಾಲೆಂಡರ್ ತೆರೆಯಲಾಗುತ್ತಿದೆ..." else "Opening Krishi Calendar..."
            }
            lowerMsg.contains("soil") || lowerMsg.contains("ಮಣ್ಣು") -> {
                if (isKannada) "ಮಣ್ಣಿನ ಮೇಲ್ವಿಚಾರಣೆ ಡೇಟಾವನ್ನು ತೋರಿಸಲಾಗುತ್ತಿದೆ..." else "Showing Soil Monitoring data..."
            }
            lowerMsg.contains("map") || lowerMsg.contains("ನಕ್ಷೆ") -> {
                if (isKannada) "ನಕ್ಷೆಯನ್ನು ತೆರೆಯಲಾಗುತ್ತಿದೆ..." else "Opening Map..."
            }
            else -> {
                if (isKannada) "'$command' ಬಗ್ಗೆ ನಿಮ್ಮ ವಿನಂತಿಯನ್ನು ನಾನು ವಿಶ್ಲೇಷಿಸುತ್ತಿದ್ದೇನೆ..." 
                else "I'm analyzing your request regarding '$command'..."
            }
        }
        
        val currentList = _chatMessages.value.toMutableList()
        currentList.add(Pair(response, false))
        _chatMessages.value = currentList
    }

    fun setListening(listening: Boolean) {
        _isListening.value = listening
    }
}
