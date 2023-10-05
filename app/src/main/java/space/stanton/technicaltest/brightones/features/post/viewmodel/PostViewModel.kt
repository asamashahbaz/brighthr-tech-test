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

    private val postRepository = PostRepository()

    var posts = MutableStateFlow<List<Post>>(emptyList())

    fun loadPost() {
        viewModelScope.launch {
            val result = postRepository.retrieveAllPosts()
            posts.emit(result.shuffled())
        }
    }

    fun navigateToDetail() {
        // TODO: For you to implement however you see fit.
    }
}