package space.stanton.technicaltest.brightones.features.post.repository

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import space.stanton.technicaltest.brightones.features.post.model.Post

class PostRepository {

    private val builder: Retrofit.Builder = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder()
                    .create()
            )
        )

    private var retrofit = builder.build()

    suspend fun retrieveAllPosts(): List<Post> {
        return retrofit.create(PostService::class.java).retrieveAllPosts()
    }

}