package es.uvigo.esei.drvidal.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import es.uvigo.esei.drvidal.R

/**
 * Created by Denís Requejo on 2020-01-17.
 */
class TasksToDoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_to_do_tasks, container, false)
    }

}