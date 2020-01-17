package es.uvigo.esei.drvidal.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import es.uvigo.esei.drvidal.R
import es.uvigo.esei.drvidal.entity.HabilityEntity
import es.uvigo.esei.drvidal.entity.UserEntity
import es.uvigo.esei.drvidal.entity.UserHabilityEntity
import es.uvigo.esei.drvidal.util.ioThread
import es.uvigo.esei.drvidal.viewmodel.HabilityViewModel
import es.uvigo.esei.drvidal.viewmodel.UserHabilityViewModel
import es.uvigo.esei.drvidal.viewmodel.UserTaskViewModel
import es.uvigo.esei.drvidal.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.toast

class LoginActivity : AppCompatActivity() {
    private lateinit var userViewModel: UserViewModel
    private lateinit var habilityViewModel: HabilityViewModel
    private lateinit var userTaskViewModel: UserTaskViewModel
    private lateinit var userHabilityViewModel: UserHabilityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        habilityViewModel = ViewModelProvider(this).get(HabilityViewModel::class.java)
        userTaskViewModel = ViewModelProvider(this).get(UserTaskViewModel::class.java)
        userHabilityViewModel = ViewModelProvider(this).get(UserHabilityViewModel::class.java)
        populateDB()
        setupUI()
        setupListeners()
    }

    private fun populateDB() {
        ioThread {
            userViewModel.insertAll(listOfUsers)
            habilityViewModel.insertAll(listOfHabilities)
            userHabilityViewModel.insertAll(listOfUserHabilities)
        }
    }

    private fun setupUI() {
        supportActionBar?.hide()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        text_username.editText?.setText("rgarcia")
        text_password.editText?.setText("rgarcia")
    }

    private fun setupListeners() {
        btn_login.onClick {
           handleLogin()
        }
    }

    private fun handleLogin() {
        btn_login.isEnabled = false
        val id = text_username.editText?.text.toString()
        val password =  text_password.editText?.text.toString()
        if(id.isBlank() || password.isBlank()) {
            toast("Introduzca credenciales")
            btn_login.isEnabled = true
        } else {
            ioThread {
                val userEntity = userViewModel.getByIdAndPassword(id, password)
                if (userEntity != null) {
                    handleLoginSuccess(userEntity)
                } else {
                    handleLoginFailure()
                }
            }
        }
    }

    private fun handleLoginSuccess(userEntity: UserEntity) {
        runOnUiThread {
            val mProgressDialog = indeterminateProgressDialog("Realizando login...")
            mProgressDialog.setCancelable(false)
            mProgressDialog.show()
            Handler().postDelayed({
                mProgressDialog.dismiss()
                btn_login.isEnabled = true
                when (userEntity.type) {
                    UserEntity.ADMIN -> {
                        val intent = Intent(this@LoginActivity, AdminActivity::class.java)
                        intent.putExtra(EXTRA_USER, userEntity)
                        startActivity(intent)
                    }
                    UserEntity.TECHNICAL -> {
                        val intent = Intent(this@LoginActivity, TechnicalActivity::class.java)
                        intent.putExtra(EXTRA_USER, userEntity)
                        startActivity(intent)
                    }
                    else -> {
                        toast("Tipo de usuario no válido")
                    }
                }
            }, 1500)
        }
    }

    private fun handleLoginFailure() {
        runOnUiThread {
            val mProgressDialog = indeterminateProgressDialog("Realizando login...")
            mProgressDialog.setCancelable(false)
            mProgressDialog.show()
            Handler().postDelayed({
                mProgressDialog.dismiss()
                btn_login.isEnabled = false
                toast("Credenciales incorrectos")
            }, 1500)
        }
    }

    companion object {
        const val EXTRA_USER =
            "es.uvigo.esei.drvidal.activity.LoginActivity.user"

        private val listOfUsers = listOf(
            UserEntity("drequejo", "drequejo", "Denís Requejo", "#gth72", UserEntity.ADMIN),
            UserEntity("rgarcia", "rgarcia", "Rubén García","#a6h12", UserEntity.ADMIN),
            UserEntity("rmartin", "rmartin", "Rafael Martín", "#bbf41"),
            UserEntity("slopez", "slopez", "Sarah Lopez", "#1f5gs"),
            UserEntity("pmartinez", "pmartinez", "Pablo Martínez", "#443dr"),
            UserEntity("lalonso", "lalonso", "Laura Alonso", "#sgf54"),
            UserEntity("efernandez", "efernandez", "Eduardo Fernández", "#hgtk1")
        )

        private val listOfHabilities = listOf(
            HabilityEntity(1, "Cobrar"),
            HabilityEntity(2, "Envolver"),
            HabilityEntity(3, "Limpiar"),
            HabilityEntity(4, "Mantener"),
            HabilityEntity(5, "Encarar")
        )

        private val listOfUserHabilities = listOf(
            UserHabilityEntity("rmartin", 1),
            UserHabilityEntity("rmartin", 2),
            UserHabilityEntity("rmartin", 4),
            UserHabilityEntity("slopez", 2),
            UserHabilityEntity("slopez", 3),
            UserHabilityEntity("pmartinez", 1),
            UserHabilityEntity("pmartinez", 5),
            UserHabilityEntity("lalonso", 2),
            UserHabilityEntity("lalonso", 3),
            UserHabilityEntity("lalonso", 5),
            UserHabilityEntity("efernandez", 4)
        )


    }
}
