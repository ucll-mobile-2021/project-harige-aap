package be.ucll.student.rafcloesen.mobieletoepassingen

import org.litote.kmongo.KMongo
import org.litote.kmongo.eq
import org.litote.kmongo.getCollection

data class Todo(val item: String)

const val uri = "mongodb://192.168.1.105:27017/"

class TodoRepository {

    private val client = KMongo.createClient(uri)
    private val database = client.getDatabase("todo")

    fun getTodos(): List<Todo> {
        val collection = database.getCollection<Todo>("main_list")
        return collection.find().toList()
    }

    fun addTodo(todo: Todo) {
        database.getCollection<Todo>("main_list").insertOne(todo)
    }

    fun removeTodo(todo: Todo) {
        database.getCollection<Todo>("main_list").deleteOne(Todo::item eq todo.item)
    }
}