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
import space.stanton.technicaltest.brightones.features.post.ui.PostScreen
import space.stanton.technicaltest.brightones.ui.theme.BrightonesTheme

/**
 * Please carefully read the README, in the root project directory,
 * in order to complete this tech task.
 */

/*
* Currently, there is no Dependency Injection happening in the application using Hilt.
* The `MainActivity` class should be annotated with `@AndroidEntryPoint` in order to use Hilt.
* Also, an Application class should be created, annotated with `@HiltAndroidApp` and added to the
* `AndroidManifest.xml`file. This will allow the application to inject dependencies using Hilt.
* Without this, there will be no dependency injection through Hilt.
* */
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