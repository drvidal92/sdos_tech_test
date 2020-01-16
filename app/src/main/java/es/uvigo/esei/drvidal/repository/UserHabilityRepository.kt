package es.uvigo.esei.drvidal.repository

import es.uvigo.esei.drvidal.dao.UserHabilityDao
import es.uvigo.esei.drvidal.entity.UserEntity
import es.uvigo.esei.drvidal.entity.UserHabilityEntity

/**
 * Created by Denís Requejo on 2020-01-15.
 */
class UserHabilityRepository(private val userHabilityDao: UserHabilityDao) {

    fun insert(user: UserHabilityEntity) {
        userHabilityDao.insert(user)
    }

    fun insertAll(users: List<UserHabilityEntity>) {
        userHabilityDao.insertAll(users)
    }

    fun getUsersByHabilityId(habilityId: Int): List<UserEntity> {
        return userHabilityDao.getUsersByHability(habilityId)
    }

}