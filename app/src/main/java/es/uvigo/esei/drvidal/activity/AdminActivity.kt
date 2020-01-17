package es.uvigo.esei.drvidal.activity

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import es.uvigo.esei.drvidal.R
import es.uvigo.esei.drvidal.entity.UserEntity
import es.uvigo.esei.drvidal.entity.UserTaskEntity
import es.uvigo.esei.drvidal.util.ioThread
import es.uvigo.esei.drvidal.viewmodel.HabilityViewModel
import es.uvigo.esei.drvidal.viewmodel.UserHabilityViewModel
import es.uvigo.esei.drvidal.viewmodel.UserTaskViewModel
import es.uvigo.esei.drvidal.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_admin.*
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


/**
 * Created by Denís Requejo on 2020-01-15.
 */
class AdminActivity : AppCompatActivity() {
    private var user: UserEntity? = null
    private lateinit var userViewModel: UserViewModel
    private lateinit var habilityViewModel: HabilityViewModel
    private lateinit var userTaskViewModel: UserTaskViewModel
    private lateinit var userHabilityViewModel: UserHabilityViewModel
    private var dateSelected: Long = System.currentTimeMillis()
    private var minutesAssigned: Int = 15
    private var habilityIdSelected = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)
        setSupportActionBar(findViewById(R.id.my_toolbar))
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        habilityViewModel = ViewModelProvider(this).get(HabilityViewModel::class.java)
        userTaskViewModel = ViewModelProvider(this).get(UserTaskViewModel::class.java)
        userHabilityViewModel =  ViewModelProvider(this).get(UserHabilityViewModel::class.java)
        handleExtras()
        setupSpinner()
        setupSeekBar()
        setupTextDate()
        setupDatePicker()
        setupListeners()
    }

    private fun handleExtras() {
        if (intent.getSerializableExtra(LoginActivity.EXTRA_USER) != null) {
            user = intent.getSerializableExtra(LoginActivity.EXTRA_USER) as? UserEntity
            if (user == null) {
                toast("Por seguridad, introduzca credenciales")
                finish()
            }
        } else {
            toast("Por seguridad, introduzca credenciales")
            finish()
        }
    }

    private fun setupDatePicker() {
        image_button_select_date.onClick {
            image_button_select_date.isEnabled = false
            alert {
                isCancelable = false
                lateinit var datePicker: DatePicker
                customView {
                    verticalLayout {
                        datePicker = datePicker {
                            minDate = System.currentTimeMillis()
                        }
                    }
                }
                yesButton {
                    val cal = Calendar.getInstance()
                    cal[Calendar.DAY_OF_MONTH] = datePicker.dayOfMonth
                    cal[Calendar.MONTH] = datePicker.month
                    cal[Calendar.YEAR] = datePicker.year
                    dateSelected = cal.timeInMillis
                    val sdf = SimpleDateFormat("dd/M/yyyy", Locale.FRANCE)
                    val currentDate = sdf.format(Date(cal.timeInMillis))
                    text_date_selected.text = currentDate
                    image_button_select_date.isEnabled = true
                }
                noButton {
                    image_button_select_date.isEnabled = true
                }
            }.show()
        }
    }

    private fun setupSpinner() {
        ioThread {
            val tasks = habilityViewModel.getAll()
            if (tasks.isNotEmpty()) {
                habilityIdSelected = tasks[0].id
            }
            runOnUiThread{
                val adapter = ArrayAdapter(this,
                    android.R.layout.simple_spinner_dropdown_item, tasks.map { it.name })
                spinner_habilities.adapter = adapter

                spinner_habilities.onItemSelectedListener = object :
                    AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(parent: AdapterView<*>,
                                                view: View, position: Int, id: Long) {
                        habilityIdSelected  = tasks[position].id
                    }

                    override fun onNothingSelected(parent: AdapterView<*>) {}
                }
            }
        }
    }

    private fun setupSeekBar() {
        val maxMillisDuration: Long = 480 * 60 * 1000
        val minMillisDuration: Long = 15 * 60 * 1000
        val stepMillisDuration: Long = 15 * 60 * 1000
        val maxDurationSeekBar = ((maxMillisDuration - minMillisDuration) / stepMillisDuration).toInt()
        seek_bar_duration.max = maxDurationSeekBar
        seek_bar_duration.setOnSeekBarChangeListener(
            object : OnSeekBarChangeListener {
                override fun onStopTrackingTouch(seekBar: SeekBar) {}
                override fun onStartTrackingTouch(seekBar: SeekBar) {}
                override fun onProgressChanged(
                    seekBar: SeekBar, progress: Int,
                    fromUser: Boolean
                ) {
                    val minutesProgress = minMillisDuration + (progress * stepMillisDuration)
                    text_seek_bar.text = getTextSeekBar(minutesProgress)
                    minutesAssigned = minutesProgress.toInt()
                }
            }
        )
    }

    private fun setupTextDate() {
        val sdf = SimpleDateFormat("dd/M/yyyy", Locale.FRANCE)
        val currentDate = sdf.format(Date())
        text_date_selected.text = currentDate
    }

    private fun getTextSeekBar(millis: Long) : String {
        return String.format("%d horas, %d minutos",
            TimeUnit.MILLISECONDS.toHours(millis),
            TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(
                TimeUnit.MILLISECONDS.toHours(millis))
        )
    }

    private fun setupListeners() {
        btn_create_task.onClick {
            btn_create_task.isEnabled = false
            val user = user
            if (user != null) {
                ioThread {
                    val users = getUsersByHabilityId(habilityIdSelected)
                    if(users.isNotEmpty()) {
                        val userTaskEntity = UserTaskEntity(user.id, habilityIdSelected,  edit_text_description.text.toString(), dateSelected, minutesAssigned)
                        userTaskViewModel.insert(userTaskEntity)
                        runOnUiThread{
                            toast("Tarea añadida correctamente")
                            alert {
                                title = "¿Quiere añadir otra tarea?"
                                isCancelable = false
                                yesButton {
                                    recreate()
                                }
                                noButton {
                                    finish()
                                }
                            }.show()
                        }
                    } else {
                        toast("No hay usuarios disponibles para esta tarea en este día")
                    }
                }
            }
        }
    }

    private fun getUsersByHabilityId(habilityId: Int): List<UserEntity> {
        return userHabilityViewModel.getUsersByHabilityId(habilityId)
        //TODO: Get user with lower work that day
    }

}