package space.stanton.technicaltest.brightones.features.post.presentation.stateholder

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import space.stanton.technicaltest.brightones.features.post.domain.model.Post
import space.stanton.technicaltest.brightones.features.post.domain.repository.PostRepository
import javax.inject.Inject

@HiltViewModel
class PostDetailViewModel @Inject constructor(
	private val savedStateHandle: SavedStateHandle,
	private val postRepository: PostRepository
): ViewModel() {

	private val _post = MutableStateFlow<Post?>(null)
	val post = _post.asStateFlow()

	init {
		loadPost()
	}

	private fun loadPost() {
		viewModelScope.launch {
			val result = postRepository.retrievePostById(savedStateHandle["postId"] ?: return@launch)
			_post.emit(result)
		}
	}

}