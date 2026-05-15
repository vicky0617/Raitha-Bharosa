package com.example.raithabharosahub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.raithabharosahub.ui.navigation.NavGraph
import com.example.raithabharosahub.ui.theme.RaithaBharosaHubTheme

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.example.raithabharosahub.data.local.UserPreferences
import com.example.raithabharosahub.utils.LocaleHelper

import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.raithabharosahub.worker.WeatherWorker
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        scheduleBackgroundTasks()
        setContent {
            val context = LocalContext.current
            val userPreferences = remember { UserPreferences(context) }
            val language by userPreferences.languageFlow.collectAsState(initial = "en")

            LaunchedEffect(language) {
                LocaleHelper.updateResources(context, language)
            }

            RaithaBharosaHubTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavGraph(navController = navController)
                }
            }
        }
    }

    private fun scheduleBackgroundTasks() {
        val weatherRequest = PeriodicWorkRequestBuilder<WeatherWorker>(3, TimeUnit.HOURS)
            .build()
        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "WeatherUpdate",
            ExistingPeriodicWorkPolicy.KEEP,
            weatherRequest
        )
    }
}
