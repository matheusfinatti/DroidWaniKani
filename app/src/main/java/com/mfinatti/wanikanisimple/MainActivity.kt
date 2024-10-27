package com.mfinatti.wanikanisimple

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mfinatti.wanikanisimple.home.ui.HomeScreen
import com.mfinatti.wanikanisimple.login.ui.LoginScreen
import com.mfinatti.wanikanisimple.login.ui.LoginViewModel
import com.mfinatti.wanikanisimple.splash.ui.SplashScreen
import com.mfinatti.wanikanisimple.splash.ui.SplashViewModel
import com.mfinatti.wanikanisimple.theme.WaniKaniSimpleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val splashViewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WaniKaniSimpleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = "splash",
                    ) {
                        composable(route = "splash") {
                            Log.d(Consts.TAG, "Recomposing Splash")
                            SplashScreen(
                                viewModel = splashViewModel,
                                onNavToLogin = {
                                    navController.navigate(
                                        route = "login",
                                    )
                                },
                                onNavToHome = { user ->
                                    Log.d(Consts.TAG, "OnNavToHome")
                                    navController.navigate(
                                        route = "home/${user.username}"
                                    )
                                }
                            )
                        }
                        composable("login") { LoginScreen(navController) }
                        composable("home") { navBackStackEntry ->
                            HomeScreen()
                        }
                    }
                }
            }
        }
    }
}
