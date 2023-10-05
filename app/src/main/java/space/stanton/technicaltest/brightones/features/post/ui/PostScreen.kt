package space.stanton.technicaltest.brightones.features.post.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import space.stanton.technicaltest.brightones.features.post.model.Post
import space.stanton.technicaltest.brightones.features.post.viewmodel.PostViewModel

@Composable
fun PostScreen(
    viewModel: PostViewModel,
    modifier: Modifier = Modifier
) {

    val posts by viewModel.posts.collectAsState()

    PostView(
        modifier = modifier,
        posts = posts,
        postViewModel = viewModel,
    )
}

/*
* The `PostView` should not need the `PostViewModel` as a parameter. It should only receive
* the `posts` state.
* By removing the `PostViewModel` as a parameter, we are decoupling the view from the ViewModel.
* This also allows us to preview the view without having to instantiate the ViewModel.
* */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostView(
    modifier: Modifier,
    posts: List<Post>,
    postViewModel: PostViewModel,
) {

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(title = { Text(text = "Post List") })
        }
    ) { padding ->
        /*
        * 1. Using `Column` means that all the posts are loaded into memory at once. This is not
        *    scalable. We should use `LazyColumn` instead which recycles the composables.
        * 2. The `Column` is not scrollable. To make it scrollable, we need to specify the
        *    `scrollable()` modifier. The `LazyColumn` is scrollable by default.
        * */
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(padding)
        ) {
            posts.filter { it.body != null }.sortedBy { it.id }.forEach {
                PostView(post = it, postViewModel = postViewModel)
                Divider(modifier = Modifier.padding(top = 8.dp, bottom = 16.dp))
            }
        }
    }
}

/*
* The ViewModel should not be passed to the child views. Instead, only the ViewModel state and the
* event handler should be passed to the view. Any events that need to be handled by the ViewModel
* should be passed to the ViewModel using a dedicated function that takes the event type as a
* parameter. This helps to decouple the view from the ViewModel.
* */
@Composable
fun PostView(post: Post, postViewModel: PostViewModel) {

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .clickable {
                // An event should be passed to the ViewModel instead of calling the ViewModel
                // function directly.
                postViewModel.navigateToDetail()
            }
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = post.title,
            style = MaterialTheme.typography.titleSmall
        )
        post.body?.let {
            Text(text = it)
        }
    }
}