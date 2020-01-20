package es.uvigo.esei.drvidal.activity

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import es.uvigo.esei.drvidal.R
import es.uvigo.esei.drvidal.adapter.TaskPageAdapter
import es.uvigo.esei.drvidal.entity.UserEntity
import es.uvigo.esei.drvidal.fragment.TasksFragment
import es.uvigo.esei.drvidal.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_technical.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.sdk27.coroutines.onClick
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
        setupListeners()
    }

    private fun handleExtras() {
        if (intent.getSerializableExtra(LoginActivity.EXTRA_USER) != null) {
            user = intent.getSerializableExtra(LoginActivity.EXTRA_USER) as? UserEntity
            if (user == null) {
                toast("Por seguridad, introduzca credenciales")
                finish()
            } else {
                title = user?.name
                val userId = user?.id
                if(userId != null) {
                    setupViewPager(userId)
                }
            }
        } else {
            toast("Por seguridad, introduzca credenciales")
            finish()
        }
    }

    private fun setupViewPager(userId: String) {
        val adapter = TaskPageAdapter(supportFragmentManager)
        adapter.addFragment(TasksFragment(false, userId), "Tareas Pendientes")
        adapter.addFragment(TasksFragment(true, userId), "Tareas Realizadas")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)
    }

    private fun setupListeners() {
        fab.onClick {
            val intent = Intent(this@TechnicalActivity, PeachesActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        handleOnCloseSession()
    }

    private fun handleOnCloseSession() {
        alert(
            "¿Desea cerrar sesión?"
        ) {
            positiveButton("Si") {
                finish()
            }
            negativeButton("No") {

            }
            isCancelable = false
        }.show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.close_session -> {
                handleOnCloseSession()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}