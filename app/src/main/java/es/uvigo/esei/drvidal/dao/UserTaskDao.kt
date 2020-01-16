package es.uvigo.esei.drvidal.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import es.uvigo.esei.drvidal.entity.UserTaskEntity

/**
 * Created by Denís Requejo on 2020-01-15.
 */
@Dao
interface UserTaskDao: BaseDao<UserTaskEntity> {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(userHabilities: List<UserTaskEntity>)
}



