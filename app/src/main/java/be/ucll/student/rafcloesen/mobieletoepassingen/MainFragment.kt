package be.ucll.student.rafcloesen.mobieletoepassingen

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController

class MainFragment : Fragment(R.layout.main_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val model = MainViewModel(TodoRepository())
        val todoList : LinearLayout = view.findViewById(R.id.todoList)

        model.todos.observe(
            viewLifecycleOwner,
            { todos ->
                todoList.removeAllViews()
                todos.forEach { todo ->
                    val item = TextView(view.context)
                    item.text = todo.item
                    item.setOnClickListener {
                        model.removeTodo(todo)
                    }
                    todoList.addView(item)
                }
            }
        )

        val fab : View = view.findViewById(R.id.fab)
        fab.setOnClickListener {
            view.findNavController().navigate(R.id.action_mainFragment_to_newTodoFragment)
        }
    }
}