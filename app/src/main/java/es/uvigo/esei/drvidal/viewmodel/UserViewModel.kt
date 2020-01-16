package es.uvigo.esei.drvidal.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import es.uvigo.esei.drvidal.database.MyRoomDatabase
import es.uvigo.esei.drvidal.entity.UserEntity
import es.uvigo.esei.drvidal.repository.UserRepository

/**
 * Created by Denís Requejo on 2020-01-15.
 */
class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: UserRepository
    init {
        val userDao = MyRoomDatabase.getInstance(
            application
        ).userDao()
        repository = UserRepository(userDao)
    }

    fun insert(user: UserEntity) {
        repository.insert(user)
    }

    fun insertAll(user: List<UserEntity>) {
        repository.insertAll(user)
    }

    fun getByIdAndPassword(id: String, password: String ): UserEntity? {
        return repository.getByIdAndPassword(id, password)
    }

}