package space.stanton.technicaltest.brightones.features.post.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import space.stanton.technicaltest.brightones.features.post.data.remote.PostService
import space.stanton.technicaltest.brightones.features.post.domain.model.Post
import space.stanton.technicaltest.brightones.features.post.domain.repository.PostRepository
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val postService: PostService
) : PostRepository {

    override suspend fun retrieveAllPosts(): List<Post> {
        val posts = withContext(Dispatchers.IO) {
            postService.retrieveAllPosts()
        }

        return posts
    }

}