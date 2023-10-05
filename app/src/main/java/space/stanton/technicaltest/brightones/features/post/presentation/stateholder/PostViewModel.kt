package space.stanton.technicaltest.brightones.features.post.presentation.stateholder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import space.stanton.technicaltest.brightones.features.post.domain.model.Post
import space.stanton.technicaltest.brightones.features.post.domain.repository.PostRepository
import space.stanton.technicaltest.brightones.features.post.presentation.navigation.AppNavigator
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val postRepository: PostRepository,
    private val appNavigator: AppNavigator
): ViewModel() {

    val navigationChannel = appNavigator.navigationChannel

    private val _posts = MutableStateFlow<List<Post>>(emptyList())
    val posts = _posts.asStateFlow()

    init {
        loadPost()
    }

    private fun loadPost() {
        viewModelScope.launch {
            val result = postRepository.retrieveAllPosts()
            _posts.emit(result.shuffled())
        }
    }

    /*
    * This function should be private. Instead, a `onEvent` function should be exposed that takes in
    * a specific `PostEvent` type and performs the appropriate action.
    * */
    fun navigateToDetail(postId: Int) {
        viewModelScope.launch {
            appNavigator.navigateTo("post/$postId")
        }
    }
}