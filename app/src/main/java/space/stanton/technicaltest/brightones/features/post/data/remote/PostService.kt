package space.stanton.technicaltest.brightones.features.post.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import space.stanton.technicaltest.brightones.features.post.domain.model.Post

interface PostService {
    @GET("posts")
    suspend fun retrieveAllPosts(): List<Post>

    @GET("posts/{id}")
    suspend fun retrievePostWithId(
        @Path("id") postId: Int
    ): Post
}