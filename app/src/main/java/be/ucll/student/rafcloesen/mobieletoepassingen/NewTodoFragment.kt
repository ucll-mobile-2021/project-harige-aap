package be.ucll.student.rafcloesen.mobieletoepassingen

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class NewTodoFragment : Fragment() {

    companion object {
        fun newInstance() = NewTodoFragment()
    }

    private lateinit var viewModel: NewTodoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.new_todo_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NewTodoViewModel::class.java)
        // TODO: Use the ViewModel
    }

}