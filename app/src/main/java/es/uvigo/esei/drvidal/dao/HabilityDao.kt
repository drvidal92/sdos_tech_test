package es.uvigo.esei.drvidal.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import es.uvigo.esei.drvidal.entity.HabilityEntity

/**
 * Created by Denís Requejo on 2020-01-15.
 */
@Dao
interface HabilityDao : BaseDao<HabilityEntity> {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(habilities: List<HabilityEntity>)

    @Query("SELECT * FROM HabilityEntity ORDER BY name DESC")
    fun getAll() : List<HabilityEntity>
}

