package be.ucll.student.rafcloesen.mobieletoepassingen

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import android.R.layout.simple_dropdown_item_1line

class NewTodoFragment : Fragment(R.layout.new_todo_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val model = NewTodoViewModel(TodoRepository())
        val list : AutoCompleteTextView = view.findViewById(R.id.list)
        val content : EditText = view.findViewById(R.id.content)
        val button : Button = view.findViewById(R.id.button)

        button.setOnClickListener {
            model.addTodo(Todo(content.text.toString()), list.text.toString())
            view.findNavController().navigate(R.id.action_newTodoFragment_to_mainFragment)
        }

        model.lists.observe(
            viewLifecycleOwner,
            { names ->
                list.setAdapter(
                    ArrayAdapter(
                        view.context,
                        simple_dropdown_item_1line,
                        names))
            }
        )
    }

}