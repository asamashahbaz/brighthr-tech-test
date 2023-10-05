package space.stanton.technicaltest.brightones

import kotlinx.coroutines.runBlocking
import org.junit.Test
import space.stanton.technicaltest.brightones.features.post.domain.model.Post
import space.stanton.technicaltest.brightones.features.post.domain.repository.PostRepository

class PostRepositoryTest {

	@Test
	fun retrieveAllPosts_shouldReturnListOfPosts() = runBlocking {
		val postRepository = FakePostRepositoryImpl()
		val postsList = postRepository.retrieveAllPosts()
		assert(postsList.isNotEmpty())
	}

	private class FakePostRepositoryImpl : PostRepository {
		override suspend fun retrieveAllPosts(): List<Post> {
			return listOf(
				Post(
					userId = 1,
					id = 1,
					title = "title",
					body = "body"
				),
				Post(
					userId = 2,
					id = 2,
					title = "title 2",
					body = "body 2"
				)
			)
		}

	}

}