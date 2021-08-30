package be.ucll.student.rafcloesen.mobieletoepassingen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewTodoViewModel constructor(private val repository: TodoRepository): ViewModel() {

    private val _lists = MutableLiveData<List<String>>()
    val lists: LiveData<List<String>> = _lists

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _lists.postValue(repository.getListNames())
        }
    }

    fun addTodo(todo: Todo, list: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTodo(todo, list)
        }
    }
}