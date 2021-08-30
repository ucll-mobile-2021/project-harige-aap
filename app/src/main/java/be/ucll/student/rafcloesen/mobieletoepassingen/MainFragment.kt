package be.ucll.student.rafcloesen.mobieletoepassingen

import android.os.Bundle
import android.view.Gravity.CENTER_HORIZONTAL
import android.view.View
import android.widget.LinearLayout
import android.widget.LinearLayout.VERTICAL
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController

class MainFragment : Fragment(R.layout.main_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val model = MainViewModel(TodoRepository())
        val listsView : LinearLayout = view.findViewById(R.id.todoLists)

        model.todos.observe(
            viewLifecycleOwner,
            { todos ->
                listsView.removeAllViews()
                todos.forEach { (listName, list) ->
                    val listView = LinearLayout(view.context)
                    listView.orientation = VERTICAL

                    val title = TextView(view.context)
                    title.text = listName
                    title.gravity = CENTER_HORIZONTAL
                    title.textSize = 24f

                    title.setOnClickListener {
                        model.removeList(listName)
                    }

                    listView.addView(title)
                    list.forEach { todo ->
                        val item = TextView(view.context)
                        item.text = todo.item
                        item.setOnClickListener {
                            model.removeTodo(todo, listName)
                        }
                        listView.addView(item)
                    }
                    listsView.addView(listView)
                }
            }
        )

        val fab : View = view.findViewById(R.id.fab)
        fab.setOnClickListener {
            view.findNavController().navigate(R.id.action_mainFragment_to_newTodoFragment)
        }
    }
}