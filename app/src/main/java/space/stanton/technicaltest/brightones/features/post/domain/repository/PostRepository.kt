package space.stanton.technicaltest.brightones.features.post.domain.repository

import space.stanton.technicaltest.brightones.features.post.domain.model.Post

interface PostRepository {

	suspend fun retrieveAllPosts(): List<Post>

	suspend fun retrievePostById(postId: Int): Post

}