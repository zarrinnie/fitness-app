package com.example.smartfit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.smartfit.onboarding.OnBoardingScreen
import com.example.smartfit.onboarding.WelcomeScreen
import com.example.smartfit.ui.theme.SmartFitTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SmartFitTheme {
                SmartFitNav()
            }
        }
    }
}

@Composable
fun SmartFitNav() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "welcome"
    ) {

        composable("welcome") {
            WelcomeScreen(
                onGetStartedClick = {
                    navController.navigate("onboarding")
                }
            )
        }

        composable("onboarding") {
            OnBoardingScreen(
                onFinish = {
                    navController.navigate("register") {
                        popUpTo("welcome") { inclusive = true }
                    }
                }
            )
        }

        composable("register") {
            RegisterScreen()
        }
    }
}
