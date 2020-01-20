package es.uvigo.esei.drvidal.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import es.uvigo.esei.drvidal.R
import es.uvigo.esei.drvidal.adapter.TaskListAdapter
import es.uvigo.esei.drvidal.entity.UserTaskEntity
import es.uvigo.esei.drvidal.util.ioThread
import es.uvigo.esei.drvidal.viewmodel.UserTaskViewModel
import kotlinx.android.synthetic.main.recycler_view.*
import org.jetbrains.anko.support.v4.runOnUiThread
import org.jetbrains.anko.support.v4.toast

/**
 * Created by Denís Requejo on 2020-01-17.
 */
class TasksFragment(private val completed: Boolean, val userId: String) : Fragment() {

    private lateinit var userTaskViewModel: UserTaskViewModel
    private lateinit var userTasks: LiveData<List<UserTaskEntity>>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.recycler_view, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userTaskViewModel = ViewModelProvider(this).get(UserTaskViewModel::class.java)
        ioThread {
            userTasks = userTaskViewModel.getAllByUserIdAndCompleted(userId,completed)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        runOnUiThread {
            userTasks.observe(viewLifecycleOwner, Observer { userTasks ->
                setAdapter(userTasks)
            })
        }
    }

    private fun setAdapter(userTasks : List<UserTaskEntity>) {
        val adapter = TaskListAdapter(this.requireContext(), completed) { userTaskEntity: UserTaskEntity -> onClickSwitchListener(userTaskEntity) }
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this.requireContext())
        adapter.setTasks(userTasks)
    }

    private fun onClickSwitchListener(userTaskEntity: UserTaskEntity) {
        userTaskEntity.completed = System.currentTimeMillis()
        ioThread {
            userTaskViewModel.insert(userTaskEntity)
            runOnUiThread {
                toast("Tarea ${userTaskEntity.habilityName} completada")
            }
        }
    }
}
