package es.uvigo.esei.drvidal.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import es.uvigo.esei.drvidal.R
import es.uvigo.esei.drvidal.adapter.TaskPageAdapter
import es.uvigo.esei.drvidal.entity.UserEntity
import es.uvigo.esei.drvidal.fragment.TasksDoneFragment
import es.uvigo.esei.drvidal.fragment.TasksToDoFragment
import es.uvigo.esei.drvidal.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_technical.*
import org.jetbrains.anko.toast

/**
 * Created by Denís Requejo on 2020-01-15.
 */
class TechnicalActivity : AppCompatActivity() {
    private var user: UserEntity? = null
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_technical)
        setSupportActionBar(toolbar)
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        handleExtras()
        val adapter = TaskPageAdapter(supportFragmentManager)
        adapter.addFragment(TasksToDoFragment(), "Tareas Pendientes")
        adapter.addFragment(TasksDoneFragment(), "Tareas Realizadas")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)
    }

    private fun handleExtras() {
        if (intent.getSerializableExtra(LoginActivity.EXTRA_USER) != null) {
            user = intent.getSerializableExtra(LoginActivity.EXTRA_USER) as? UserEntity
            if (user == null) {
                toast("Por seguridad, introduzca credenciales")
                finish()
            } else {
                title = user?.name
            }
        } else {
            toast("Por seguridad, introduzca credenciales")
            finish()
        }
    }
}