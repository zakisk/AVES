package com.aves.ui.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.aves.util.Constants.PHOTO_ID


sealed class Screen(val route: String) {

    object PhotosScreen : Screen("photos")

    object PhotoDetailScreen : Screen("photo_detail") {
        val arguments: List<NamedNavArgument> =
            listOf(
                navArgument(PHOTO_ID) { type = NavType.StringType },
            )
    }

    object ProfileScreen : Screen("profile") {
        val arguments: List<NamedNavArgument> =
            listOf(
                navArgument(PHOTO_ID) { type = NavType.StringType },
            )
    }

}
