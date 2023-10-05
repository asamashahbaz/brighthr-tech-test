package space.stanton.technicaltest.brightones.features.post.repository

import retrofit2.http.GET
import retrofit2.http.Path
import space.stanton.technicaltest.brightones.features.post.model.Post

interface PostService {
    @GET("posts")
    suspend fun retrieveAllPosts(): List<Post>

    @GET("posts/{id}")
    suspend fun retrievePostWithId(
        @Path("id") postId: Int
    ): Post
}