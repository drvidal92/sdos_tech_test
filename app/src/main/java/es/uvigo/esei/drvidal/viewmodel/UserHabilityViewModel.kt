package es.uvigo.esei.drvidal.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import es.uvigo.esei.drvidal.database.MyRoomDatabase
import es.uvigo.esei.drvidal.entity.UserEntity
import es.uvigo.esei.drvidal.entity.UserHabilityEntity
import es.uvigo.esei.drvidal.repository.UserHabilityRepository

/**
 * Created by Denís Requejo on 2020-01-15.
 */
class UserHabilityViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: UserHabilityRepository
    init {
        val userHabilityDao = MyRoomDatabase.getInstance(
            application
        ).userHabilityDao()
        repository = UserHabilityRepository(userHabilityDao)
    }

    fun insert(user: UserHabilityEntity) {
        repository.insert(user)
    }

    fun insertAll(user: List<UserHabilityEntity>) {
        repository.insertAll(user)
    }

    fun getUsersByHabilityId(habilityId: Int): List<UserEntity> {
        return repository.getUsersByHabilityId(habilityId)
    }


}