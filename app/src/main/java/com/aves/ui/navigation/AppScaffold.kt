package com.aves.ui.navigation

import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


@Composable
fun AppScaffold(navController: NavHostController = rememberNavController()) {

    val scaffoldState = rememberScaffoldState()
    Scaffold(scaffoldState = scaffoldState) { innerPadding ->
        AppNavHost(
            navController = navController,
            innerPadding = innerPadding
        )
    }
}