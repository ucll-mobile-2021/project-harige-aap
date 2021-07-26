package be.ucll.student.rafcloesen.mobieletoepassingen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel constructor(private val repository: TodoRepository): ViewModel() {
    private val _todos = MutableLiveData<List<Todo>>()
    val todos: LiveData<List<Todo>> = _todos

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _todos.postValue(repository.getTodos())
        }
    }

    fun removeTodo(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.removeTodo(todo)
        }
        _todos.value = _todos.value?.minus(todo)
    }
}