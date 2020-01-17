package es.uvigo.esei.drvidal.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import es.uvigo.esei.drvidal.R
import es.uvigo.esei.drvidal.entity.UserTaskEntity

/**
 * Created by Denís Requejo on 2019-10-30.
 */
class TaskListAdapter internal constructor(context: Context, val switchClickListener: (UserTaskEntity) -> Unit) :
    RecyclerView.Adapter<TaskListAdapter.TaskViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var tasks = emptyList<UserTaskEntity>() // Cached copy of refuels

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textTaskName: TextView = itemView.findViewById(R.id.text_task_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val itemView = inflater.inflate(R.layout.item_task_to_do , parent, false)
        return TaskViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val current = tasks[position]
        holder.textTaskName.text = current.description
        holder.textTaskName.setOnClickListener { switchClickListener(current) }
    }

    internal fun setTasks(taskEntities: List<UserTaskEntity>) {
        this.tasks = taskEntities
        notifyDataSetChanged()
    }

    override fun getItemCount() = tasks.size

}