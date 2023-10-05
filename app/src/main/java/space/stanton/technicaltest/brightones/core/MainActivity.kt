package space.stanton.technicaltest.brightones.core

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import space.stanton.technicaltest.brightones.features.post.presentation.ui.PostScreen
import space.stanton.technicaltest.brightones.features.post.presentation.stateholder.PostViewModel
import space.stanton.technicaltest.brightones.core.ui.theme.BrightonesTheme
import space.stanton.technicaltest.brightones.features.post.presentation.navigation.NavigationIntent
import space.stanton.technicaltest.brightones.features.post.presentation.stateholder.PostDetailViewModel
import space.stanton.technicaltest.brightones.features.post.presentation.ui.PostDetailScreen

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
        val postViewModel: PostViewModel = viewModel()

        NavigationEffects(
            navigationChannel = postViewModel.navigationChannel,
            navHostController = navHostController
        )

        NavHost(navController = navHostController, startDestination = "post") {
            composable(route = "post") {
                PostScreen(
                    viewModel = postViewModel,
                    modifier = Modifier.fillMaxSize()
                )
            }

            composable(
                route = "post/{postId}",
                arguments = listOf(navArgument("postId") { type = NavType.IntType })
            ) {
                val postDetailViewModel: PostDetailViewModel = hiltViewModel()

                PostDetailScreen(
                    viewModel = postDetailViewModel
                )
            }
        }
    }
}

@Composable
fun NavigationEffects(
    navigationChannel: Channel<NavigationIntent>,
    navHostController: NavHostController
) {
    val activity = (LocalContext.current as? Activity)
    LaunchedEffect(activity, navHostController, navigationChannel) {
        navigationChannel.receiveAsFlow().collect { intent ->
            if (activity?.isFinishing == true) {
                return@collect
            }
            when (intent) {
                is NavigationIntent.NavigateBack -> {
                    if (intent.route != null) {
                        navHostController.popBackStack(intent.route, intent.inclusive)
                    } else {
                        navHostController.popBackStack()
                    }
                }
                is NavigationIntent.NavigateTo -> {
                    navHostController.navigate(intent.route) {
                        launchSingleTop = intent.isSingleTop
                        intent.popUpToRoute?.let { popUpToRoute ->
                            popUpTo(popUpToRoute) { inclusive = intent.inclusive }
                        }
                    }
                }
            }
        }
    }
}
