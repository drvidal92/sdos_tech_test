package es.uvigo.esei.drvidal.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import es.uvigo.esei.drvidal.R
import es.uvigo.esei.drvidal.entity.UserTaskEntity
import es.uvigo.esei.drvidal.util.transformToHoursAndMinutes
import es.uvigo.esei.drvidal.util.transformToYearMonthDay

/**
 * Created by Denís Requejo on 2019-10-30.
 */
class TaskListAdapter internal constructor(context: Context,
                                           private var tasksCompleted: Boolean, val switchClickListener: (UserTaskEntity) -> Unit) :
    RecyclerView.Adapter<TaskListAdapter.TaskViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var tasks = emptyList<UserTaskEntity>()

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textName: TextView = itemView.findViewById(R.id.text_task_name)
        val textDescription: TextView = itemView.findViewById(R.id.text_task_description)
        val textDuration: TextView = itemView.findViewById(R.id.text_duration)
        val dateAssigned: TextView = itemView.findViewById(R.id.text_date_assigned)
        val switchCompletedTask: Switch = itemView.findViewById(R.id.switch_task_done)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val itemView = inflater.inflate(R.layout.item_task , parent, false)
        return TaskViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val current = tasks[position]
        holder.textName.text = current.habilityName
        holder.textDescription.text = if (current.description.isBlank()) "Sin descripción" else current.description
        holder.textDuration.text = (current.minutesDuration * 60 * 1000).toLong().transformToHoursAndMinutes()
        holder.dateAssigned.text = current.assigned.transformToYearMonthDay()
        if (tasksCompleted) {
            holder.switchCompletedTask.visibility = View.GONE
        } else {
            holder.switchCompletedTask.visibility = View.VISIBLE
            holder.switchCompletedTask.setOnClickListener { switchClickListener(current) }
        }
    }

    internal fun setTasks(taskEntities: List<UserTaskEntity>) {
        this.tasks = taskEntities
        notifyDataSetChanged()
    }

    override fun getItemCount() = tasks.size

}