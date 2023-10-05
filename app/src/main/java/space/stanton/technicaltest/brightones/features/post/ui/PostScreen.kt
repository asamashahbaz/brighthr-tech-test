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
fun PostScreen(modifier: Modifier = Modifier) {
    val viewModel = PostViewModel()

    val posts by viewModel.posts.collectAsState()

    PostView(
        modifier = modifier,
        posts = posts,
        postViewModel = viewModel,
        loadPost = viewModel::loadPost
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostView(
    modifier: Modifier,
    posts: List<Post>,
    postViewModel: PostViewModel,
    loadPost: () -> Unit
) {

    loadPost()

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(title = { Text(text = "Post List") })
        }
    ) { padding ->
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

@Composable
fun PostView(post: Post, postViewModel: PostViewModel) {

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .clickable {
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