package be.ucll.student.rafcloesen.mobieletoepassingen

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val model = MainViewModel(TodoRepository())
        val todoList = findViewById<LinearLayout>(R.id.todoList)
        val item = TextView(this)
        item.text = "Hello World!"
        todoList.addView(item)

        model.todos.observe(
            this,
            { todos -> item.text = todos.first().item })
    }
}