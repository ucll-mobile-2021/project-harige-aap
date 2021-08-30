package be.ucll.student.rafcloesen.mobieletoepassingen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel constructor(private val repository: TodoRepository): ViewModel() {
    private val _todos = MutableLiveData<Map<String, List<Todo>>>()
    val todos: LiveData<Map<String, List<Todo>>> = _todos

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _todos.postValue(
                repository.getListNames().map {
                    it to repository.getList(it)
                }.toMap()
            )
        }
    }

    fun removeTodo(todo: Todo, list: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.removeTodo(todo, list)
        }
        _todos.value = _todos.value?.map { (k, v) ->
            if (k != list) {
                k to v
            }
            else {
                k to v.minus(todo)
            }
        }?.toMap()
    }

    fun removeList(list: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.removeList(list)
        }

        _todos.value = _todos.value?.minus(list)
    }
}