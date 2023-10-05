package space.stanton.technicaltest.brightones

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import space.stanton.technicaltest.brightones.features.post.ui.PostScreen
import space.stanton.technicaltest.brightones.ui.theme.BrightonesTheme

/**
 * Please carefully read the README, in the root project directory,
 * in order to complete this tech task.
 */

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BrightonesTheme {
                MainNavigation()
            }
        }
    }

    @Composable
    fun MainNavigation() {
        val navHostController = rememberNavController()
        NavHost(navController = navHostController, startDestination = "post") {
            composable(route = "post") {
                PostScreen(modifier = Modifier.fillMaxSize())
            }
        }
    }
}