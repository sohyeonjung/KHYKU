package com.example.khyku.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.khyku.roomDB.Post
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PostViewModelFactory(private val postRepository: PostRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PostViewModel::class.java)) {
            return PostViewModel(postRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
class PostViewModel(private val postRepository:PostRepository):ViewModel(){

    private var _postList = MutableStateFlow<List<Post>>(emptyList())
    val postList = _postList.asStateFlow()

    fun InsertPost(post:Post){
        viewModelScope.launch {
            postRepository.InsertPost(post)
            getAllItems()
        }
    }
    fun UpdatePost(post:Post){
        viewModelScope.launch {
            postRepository.UpdatePost(post)
            getAllItems()
        }
    }
    fun DeletePost(post:Post){
        viewModelScope.launch {
            postRepository.DeletePost(post)
            getAllItems()
        }
    }
    fun getAllItems(){
        viewModelScope.launch {
            postRepository.getAllItems().collect{
                _postList.value = it
            }
        }
    }

    fun getItems(postName:String){
        viewModelScope.launch {
            postRepository.getItems(postName).collect(){
                _postList.value = it
            }
        }
    }
//    fun getItem(postId: Int): Flow<Post?> {
//        return flow {
//            val post = postRepository.getItem(postId)
//            emit(post)
//        }
//    }





}