package es.uvigo.esei.drvidal.fragment

/**
 * Created by Denís Requejo on 2020-01-17.
 */
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import es.uvigo.esei.drvidal.R
import es.uvigo.esei.drvidal.adapter.TaskListAdapter
import es.uvigo.esei.drvidal.entity.UserTaskEntity
import es.uvigo.esei.drvidal.viewmodel.UserTaskViewModel
import kotlinx.android.synthetic.main.fragment_to_do_tasks.*


/**
 * A simple [Fragment] subclass.
 */
class TasksDoneFragment : Fragment() {

    private lateinit var userTaskViewModel: UserTaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_done_tasks, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userTaskViewModel = ViewModelProvider(this).get(UserTaskViewModel::class.java)

        val adapter = TaskListAdapter(this.requireContext()) { userTaskEntity: UserTaskEntity -> onClickSwitchListener(userTaskEntity) }
        recycler_view_tasks_to_do.adapter = adapter
        recycler_view_tasks_to_do.layoutManager = LinearLayoutManager(this.requireContext())
        val userTasks = userTaskViewModel.getAllByUserId("slopez")
        adapter.setTasks(userTasks)
    }

    private fun onClickSwitchListener(userTaskEntity: UserTaskEntity) {
    }
}