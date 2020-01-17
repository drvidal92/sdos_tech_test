package es.uvigo.esei.drvidal.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import es.uvigo.esei.drvidal.database.MyRoomDatabase
import es.uvigo.esei.drvidal.entity.UserTaskEntity
import es.uvigo.esei.drvidal.repository.UserTaskRepository

/**
 * Created by Denís Requejo on 2020-01-15.
 */
class UserTaskViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: UserTaskRepository
    init {
        val userTaskDao = MyRoomDatabase.getInstance(
            application
        ).userTaskDao()
        repository = UserTaskRepository(userTaskDao)
    }
    fun insert(userTask: UserTaskEntity) {
        repository.insert(userTask)
    }

    fun getAllByUserId(userId: String) : List<UserTaskEntity> {
        return repository.getAllByUserId(userId)
    }
}