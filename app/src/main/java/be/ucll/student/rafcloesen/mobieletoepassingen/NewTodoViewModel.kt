package be.ucll.student.rafcloesen.mobieletoepassingen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewTodoViewModel constructor(private val repository: TodoRepository): ViewModel() {
    fun addTodo(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTodo(todo)
        }
    }
}