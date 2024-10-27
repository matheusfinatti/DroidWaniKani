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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mfinatti.wanikanisimple.home.ui.HomeScreen
import com.mfinatti.wanikanisimple.home.ui.HomeViewModel
import com.mfinatti.wanikanisimple.login.navigation.loginScreen
import com.mfinatti.wanikanisimple.login.navigation.navigateToLogin
import com.mfinatti.wanikanisimple.login.ui.LoginScreen
import com.mfinatti.wanikanisimple.login.ui.LoginViewModel
import com.mfinatti.wanikanisimple.navigation.WKNavHost
import com.mfinatti.wanikanisimple.splash.ui.SplashScreen
import com.mfinatti.wanikanisimple.splash.ui.SplashViewModel
import com.mfinatti.wanikanisimple.theme.WaniKaniSimpleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WaniKaniSimpleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    WKNavHost(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
