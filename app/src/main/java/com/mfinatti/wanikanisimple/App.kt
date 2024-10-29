package com.mfinatti.wanikanisimple

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import com.mfinatti.wanikanisimple.navigation.TopLevelDestination
import com.mfinatti.wanikanisimple.navigation.WKNavHost
import com.mfinatti.wanikanisimple.theme.WaniKaniSimpleTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination
import com.mfinatti.wanikanisimple.ui.UserCard
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Suppress("ModifierMissing")
@Composable
fun DroidWaniKaniApp(
    appState: AppState,
    mainActivityViewModel: MainActivityViewModel = hiltViewModel(),
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    var selectedDestination by rememberSaveable {
        mutableStateOf(TopLevelDestination.home)
    }

    val user by mainActivityViewModel.user.collectAsStateWithLifecycle()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(modifier = Modifier.width(200.dp)) {
                UserCard(user)
                HorizontalDivider()
                appState.topLevelDestinations.forEach { destination ->
                    val selected = selectedDestination == destination
                    NavigationDrawerItem(
                        label = { Text(stringResource(destination.labelResId)) },
                        selected = selected,
                        onClick = {
                            appState.coroutineScope.launch { drawerState.close() }
                            selectedDestination = destination
                            appState.navigateToTopLevelDestination(destination)
                        }
                    )
                }
            }
        },
    ) {
        WaniKaniSimpleTheme {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                topBar = {
                    if (shouldShowTopBar(appState.currentDestination)) {
                        CenterAlignedTopAppBar(
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = Color.White,
                                titleContentColor = Color.Black,
                            ),
                            title = {
                                Text("WaniKani")
                            },
                            navigationIcon = {
                                if (appState.shouldShowUpNavigation()) {
                                    IconButton(onClick = { appState.navController.popBackStack() }) {
                                        Icon(
                                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                            contentDescription = null,
                                        )
                                    }
                                } else {
                                    IconButton(onClick = {
                                        appState.coroutineScope.launch { drawerState.open() }
                                    }) {
                                        Icon(
                                            imageVector = Icons.Filled.Menu,
                                            contentDescription = null,
                                        )
                                    }
                                }
                            },
                        )
                    }
                },
            ) { innerPadding ->
                WKNavHost(
                    navController = appState.navController,
                    modifier = Modifier.padding(innerPadding)
                )

            }
        }
    }
}

private fun shouldShowTopBar(currentDestination: NavDestination?): Boolean =
    currentDestination?.route !in listOf("login", "splash")
