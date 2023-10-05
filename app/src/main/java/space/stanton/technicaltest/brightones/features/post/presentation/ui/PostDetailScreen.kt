package space.stanton.technicaltest.brightones.features.post.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import space.stanton.technicaltest.brightones.features.post.domain.model.Post
import space.stanton.technicaltest.brightones.features.post.presentation.stateholder.PostDetailViewModel

@Composable
fun PostDetailScreen(
	viewModel: PostDetailViewModel
) {
	val post by viewModel.post.collectAsState()

	PostDetailView(post = post)
}

@Composable
fun PostDetailView(
	post: Post?,
) {
	Column(
		modifier = Modifier
			.fillMaxSize(),
		verticalArrangement = Arrangement.spacedBy(8.dp)
	) {
		if (post != null) {
			Text(
				text = post.title,
				style = TextStyle(
					fontWeight = FontWeight.Bold,
					fontSize = 20.sp
				)
			)
			post.body?.let {
				Text(text = it)
			}
		}
	}
}