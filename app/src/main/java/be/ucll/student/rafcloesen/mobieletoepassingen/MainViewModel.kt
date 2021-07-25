package be.ucll.student.rafcloesen.mobieletoepassingen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mongodb.client.MongoClients
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _todos = MutableLiveData<List<String>>()
    val todos: LiveData<List<String>> = _todos

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val uri = "mongodb://192.168.1.105:27017/"
            val mongoClient = MongoClients.create(uri)
            val database = mongoClient.getDatabase("todo")
            val collection = database.getCollection("main_list")
            val document = collection.find().first()
            _todos.postValue(document?.get("item")?.let { mutableListOf(it.toString()) })
        }
    }
}