package com.example.khyku.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.khyku.roomDB.Post
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PostViewModelFactory(private val repository: Repository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PostViewModel::class.java)) {
            return PostViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
class PostViewModel(private val repository:Repository):ViewModel(){

    private var _postList = MutableStateFlow<List<Post>>(emptyList())
    val postList = _postList.asStateFlow()

    fun InsertPost(post:Post){
        viewModelScope.launch {
            repository.InsertPost(post)
            getAllItems()
        }
    }
    fun UpdatePost(post:Post){
        viewModelScope.launch {
            repository.UpdatePost(post)
            getAllItems()
        }
    }
    fun DeletePost(post:Post){
        viewModelScope.launch {
            repository.DeletePost(post)
            getAllItems()
        }
    }
    fun getAllItems(){
        viewModelScope.launch {
            repository.getAllItems().collect{
                _postList.value = it
            }
        }
    }
}