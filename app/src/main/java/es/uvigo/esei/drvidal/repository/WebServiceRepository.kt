package es.uvigo.esei.drvidal.repository

import android.util.Log
import androidx.lifecycle.LiveData
import es.uvigo.esei.drvidal.dao.WebServiceDao
import es.uvigo.esei.drvidal.entity.WebServiceEntity
import es.uvigo.esei.drvidal.model.Webservice
import es.uvigo.esei.drvidal.network.RetrofitFactory
import es.uvigo.esei.drvidal.util.ioThread
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Created by Denís Requejo on 2020-01-15.
 */
class WebServiceRepository(private val webServiceDao: WebServiceDao) {

    fun insertAll(webServices: List<WebServiceEntity>) {
        webServiceDao.insertAll(webServices)
    }

    fun getAll(): LiveData<List<WebServiceEntity>> {
        if (!webServiceDao.existItems()) {
           CoroutineScope(Dispatchers.IO).launch {
               val response = RetrofitFactory.makeRetrofitService().getPeaches("Fruit", "Peaches")
               withContext(Dispatchers.Main) {
                   try {
                       if (response.isSuccessful) {
                           val items = response.body()
                           if (items != null && items.isNotEmpty()) {
                               saveItems(items)
                           } else {}
                       } else {
                           Log.e("getAll Webservice items",response.code().toString())
                       }
                   } catch (e: Throwable) {
                       Log.e("getAll Webservice items","Ooops: Something else went wrong")
                   }
               }
           }
        }
        return webServiceDao.getAll()
    }

    private fun saveItems( items: List<Webservice>) {
        val webServiceItems: MutableList<WebServiceEntity> = ArrayList()
        for (item in items) {
            val webServiceEntity = WebServiceEntity(
                item.getFarmerId(),
                item.getFarmName(),
                item.getCategory(),
                item.getZipcode()
            )
            webServiceItems.add(webServiceEntity)
        }
        ioThread {
            insertAll(webServiceItems)
        }
    }
}