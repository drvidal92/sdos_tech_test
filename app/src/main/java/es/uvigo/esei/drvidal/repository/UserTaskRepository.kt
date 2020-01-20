package es.uvigo.esei.drvidal.repository

import androidx.lifecycle.LiveData
import es.uvigo.esei.drvidal.dao.UserTaskDao
import es.uvigo.esei.drvidal.entity.UserTaskEntity

/**
 * Created by Denís Requejo on 2020-01-15.
 */
class UserTaskRepository(private val userTaskDao: UserTaskDao) {

    fun insert(userTask: UserTaskEntity) {
        userTaskDao.insert(userTask)
    }

    fun getAllByUserIdAndCompleted(userId: String, completed: Boolean) : LiveData<List<UserTaskEntity>> {
        return if (completed) {
            userTaskDao.getAllByUserIdAndCompleted(userId)
        } else {
            userTaskDao.getAllByUserIdAndPending(userId)
        }
    }
}