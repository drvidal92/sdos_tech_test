package es.uvigo.esei.drvidal.repository

import es.uvigo.esei.drvidal.dao.UserDao
import es.uvigo.esei.drvidal.entity.UserEntity

/**
 * Created by Denís Requejo on 2020-01-15.
 */
class UserRepository(private val userDao: UserDao) {

    fun insert(user: UserEntity) {
        userDao.insert(user)
    }

    fun insertAll(users: List<UserEntity>) {
        userDao.insertAll(users)
    }

    fun getByIdAndPassword(id: String, password: String ): UserEntity? {
        return userDao.getByIdAndPassword(id, password)
    }
}