package es.uvigo.esei.drvidal.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import es.uvigo.esei.drvidal.database.MyRoomDatabase
import es.uvigo.esei.drvidal.entity.HabilityEntity
import es.uvigo.esei.drvidal.repository.HabilityRepository

/**
 * Created by Denís Requejo on 2020-01-15.
 */
class HabilityViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: HabilityRepository
    init {
        val taskDao = MyRoomDatabase.getInstance(
            application
        ).taskDao()
        repository = HabilityRepository(taskDao)
    }

    fun insert(hability: HabilityEntity) {
        repository.insert(hability)
    }

    fun insertAll(habilities: List<HabilityEntity>) {
        repository.insertAll(habilities)
    }

    fun getAll() : List<HabilityEntity> {
        return repository.getAll()
    }

}