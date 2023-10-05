package space.stanton.technicaltest.brightones.features.post.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import space.stanton.technicaltest.brightones.features.post.model.Post
import space.stanton.technicaltest.brightones.features.post.repository.PostRepository
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(): ViewModel() {

    /*
    * The `PostRepository` should be injected into the `PostViewModel` using Hilt. This will allow
    * the `PostRepository` to be mocked in tests. The `PostRepository` should not be instantiated
    * directly in the `PostViewModel`. This introduces tight coupling between the `PostViewModel`
    * and the `PostRepository` and violates the Dependency Inversion Principle as well as the
    * Single Responsibility Principle.
    * */
    private val postRepository = PostRepository()

    /*
    * Data encapsulation is not being used here. The `posts` state is being exposed as a public
    * property instead of a private property with a public getter. This means that the `posts` state
    * can be modified from outside the `PostViewModel` class.
    * */
    var posts = MutableStateFlow<List<Post>>(emptyList())

    /*
    * It is PostViewModel's job to load and keep the data ready. This function should be private and
    * should be called when the `PostViewModel` is instantiated.
    * */
    fun loadPost() {
        viewModelScope.launch {
            val result = postRepository.retrieveAllPosts()
            posts.emit(result.shuffled())
        }
    }

    /*
    * This function should be private. Instead, a `onEvent` function should be exposed that takes in
    * a specific `PostEvent` type and performs the appropriate action.
    * */
    fun navigateToDetail() {
        // TODO: For you to implement however you see fit.
    }
}