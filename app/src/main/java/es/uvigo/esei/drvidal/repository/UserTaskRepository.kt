package es.uvigo.esei.drvidal.repository

import es.uvigo.esei.drvidal.dao.UserTaskDao
import es.uvigo.esei.drvidal.entity.UserTaskEntity

/**
 * Created by Denís Requejo on 2020-01-15.
 */
class UserTaskRepository(private val userTaskDao: UserTaskDao) {

    fun insert(userTask: UserTaskEntity) {
        userTaskDao.insert(userTask)
    }

}