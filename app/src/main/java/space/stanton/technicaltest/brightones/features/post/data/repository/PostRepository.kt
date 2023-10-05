package space.stanton.technicaltest.brightones.features.post.data.repository

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import space.stanton.technicaltest.brightones.features.post.data.remote.PostService
import space.stanton.technicaltest.brightones.features.post.domain.model.Post

/*
* A `PostRepository` interface should be created and this class should implement it. This will
* allow the repository to be mocked in tests and will also allow the repository to be swapped out
* for a different implementation in the future.
* */
class PostRepository {

    private val builder: Retrofit.Builder = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder()
                    .create()
            )
        )

    /*
    * Since we are using Hilt, the Retrofit instance should be provided using a Hilt module and the
    * service should be injected into the repository.
    * */
    private var retrofit = builder.build()

    suspend fun retrieveAllPosts(): List<Post> {
        /*
        * The service should be provided using a Hilt module and injected into the repository.
        * The dispatcher should be switched to `IO` since this is a network call.
        * */
        return retrofit.create(PostService::class.java).retrieveAllPosts()
    }

}