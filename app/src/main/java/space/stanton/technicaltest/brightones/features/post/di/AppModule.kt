package space.stanton.technicaltest.brightones.features.post.di

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import space.stanton.technicaltest.brightones.features.post.data.remote.PostService
import space.stanton.technicaltest.brightones.features.post.data.repository.PostRepositoryImpl
import space.stanton.technicaltest.brightones.features.post.domain.repository.PostRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

	@Provides
	@Singleton
	fun provideRetrofit(): Retrofit {
		return Retrofit.Builder()
			.baseUrl("https://jsonplaceholder.typicode.com/")
			.addConverterFactory(
				GsonConverterFactory.create(
					GsonBuilder()
						.create()
				)
			)
			.build()
	}

	@Provides
	@Singleton
	fun providePostsService(retrofit: Retrofit): PostService {
		return retrofit.create(PostService::class.java)
	}

	@Provides
	@Singleton
	fun providePostRepository(postService: PostService): PostRepository {
		return PostRepositoryImpl(postService)
	}

}