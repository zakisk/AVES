package com.aves.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.aves.ui.photo_detail_screen.PhotoDetailScreen
import com.aves.ui.photos_screen.PhotosScreen
import com.aves.ui.profile_screen.ProfileScreen
import com.aves.util.Constants.PHOTO_ID


@Composable
fun AppNavHost(
    navController: NavHostController,
    innerPadding: PaddingValues
) {

    NavHost(
        modifier = Modifier.padding(innerPadding),
        navController = navController,
        startDestination = Screen.PhotosScreen.route
    ) {
        composable(Screen.PhotosScreen.route) {
            PhotosScreen(navController = navController)
        }

        composable(
            Screen.PhotoDetailScreen.route + "/{$PHOTO_ID}",
            arguments = Screen.PhotoDetailScreen.arguments
        ) {
            PhotoDetailScreen(navController = navController)
        }

        composable(
            Screen.ProfileScreen.route + "/{$PHOTO_ID}",
            arguments = Screen.ProfileScreen.arguments
        ) {
            ProfileScreen(navController = navController)
        }
    }
}