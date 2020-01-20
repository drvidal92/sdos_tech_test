package es.uvigo.esei.drvidal.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import es.uvigo.esei.drvidal.database.MyRoomDatabase
import es.uvigo.esei.drvidal.entity.WebServiceEntity
import es.uvigo.esei.drvidal.repository.WebServiceRepository

/**
 * Created by Denís Requejo on 2020-01-15.
 */
class WebServiceViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: WebServiceRepository
    init {
        val webServiceDao = MyRoomDatabase.getInstance(
            application
        ).webServiceDao()
        repository = WebServiceRepository(webServiceDao)
    }

    fun insertAll(webServices: List<WebServiceEntity>) {
        repository.insertAll(webServices)
    }

    fun getAll(): LiveData<List<WebServiceEntity>> {
        return repository.getAll()
    }

}