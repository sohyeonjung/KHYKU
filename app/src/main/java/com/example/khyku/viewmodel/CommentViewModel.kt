package com.example.khyku.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.khyku.roomDB.Comment
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CommentViewModelFactory(private val commentRepository: CommentRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CommentViewModel::class.java)) {
            return CommentViewModel(commentRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
class CommentViewModel(private val commentRepository:CommentRepository):ViewModel(){

    private var _commentList = MutableStateFlow<List<Comment>>(emptyList())
    val commentList = _commentList.asStateFlow()

    fun InsertComment(comment:Comment){
        viewModelScope.launch {
            commentRepository.InsertComment(comment)
            getAllItems()
        }
    }
//    fun UpdatePost(post:Post){
//        viewModelScope.launch {
//            postRepository.UpdatePost(post)
//            getAllItems()
//        }
//    }
//    fun DeletePost(post:Post){
//        viewModelScope.launch {
//            postRepository.DeletePost(post)
//            getAllItems()
//        }
//    }
    fun getAllItems(){
        viewModelScope.launch {
            commentRepository.getAllItems().collect{
                _commentList.value = it
            }
        }
    }

//    fun getItems(postName:String){
//        viewModelScope.launch {
//            postRepository.getItems(postName).collect(){
//                _postList.value = it
//            }
//        }
//    }


}