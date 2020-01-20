package es.uvigo.esei.drvidal.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import es.uvigo.esei.drvidal.entity.WebServiceEntity

/**
 * Created by Denís Requejo on 2020-01-15.
 */
@Dao
interface WebServiceDao: BaseDao<WebServiceEntity>{

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(users: List<WebServiceEntity>)

    @Query("SELECT * FROM WebServiceEntity ORDER BY farmName ASC")
    fun getAll() : LiveData<List<WebServiceEntity>>

    @Query("SELECT 1 FROM WebServiceEntity")
    fun existItems() : Boolean
}