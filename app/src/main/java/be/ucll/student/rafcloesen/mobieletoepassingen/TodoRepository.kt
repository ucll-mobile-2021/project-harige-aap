package be.ucll.student.rafcloesen.mobieletoepassingen

import org.litote.kmongo.KMongo
import org.litote.kmongo.eq
import org.litote.kmongo.getCollection

data class Todo(val item: String)

const val uri = "mongodb://192.168.1.105:27017/"

class TodoRepository {

    private val client = KMongo.createClient(uri)
    private val database = client.getDatabase("todo")

    fun getListNames(): List<String> {
        return database.listCollectionNames().toList()
    }

    fun getList(list: String): List<Todo> {
        return database.getCollection<Todo>(list).find().toList()
    }

    fun removeList(list: String) {
        database.getCollection(list).drop()
    }

    fun addTodo(todo: Todo, list: String) {
        database.getCollection<Todo>(list).insertOne(todo)
    }

    fun removeTodo(todo: Todo, list: String) {
        database.getCollection<Todo>(list).deleteOne(Todo::item eq todo.item)
    }
}