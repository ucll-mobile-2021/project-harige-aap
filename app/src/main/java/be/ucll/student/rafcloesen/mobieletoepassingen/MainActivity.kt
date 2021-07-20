package be.ucll.student.rafcloesen.mobieletoepassingen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.viewModels

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val model: MainViewModel by viewModels()
        val todoList = findViewById<LinearLayout>(R.id.todoList)
        val item = TextView(this)
        item.text = "Hello World!"
        todoList.addView(item)

        model.getTodos().observe(
            this,
            { todos -> item.text = todos.first() })
    }
}