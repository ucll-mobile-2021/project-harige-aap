package be.ucll.student.rafcloesen.mobieletoepassingen

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController

class NewTodoFragment : Fragment(R.layout.new_todo_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val model = NewTodoViewModel(TodoRepository())
        val content : EditText = view.findViewById(R.id.content)
        val button : Button = view.findViewById(R.id.button)

        button.setOnClickListener {
            model.addTodo(Todo(content.text.toString()))
            view.findNavController().navigate(R.id.action_newTodoFragment_to_mainFragment)
        }
    }

}