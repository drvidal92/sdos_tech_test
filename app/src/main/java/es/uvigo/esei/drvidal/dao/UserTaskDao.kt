package es.uvigo.esei.drvidal.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import es.uvigo.esei.drvidal.entity.UserTaskEntity

/**
 * Created by Denís Requejo on 2020-01-15.
 */
@Dao
interface UserTaskDao: BaseDao<UserTaskEntity> {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(userHabilities: List<UserTaskEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(userTask: UserTaskEntity)

    @Query("SELECT * FROM UserTaskEntity WHERE userId = :userId AND completed IS NOT NULL ORDER BY assigned ASC")
    fun getAllByUserIdAndCompleted(userId: String) : LiveData<List<UserTaskEntity>>

    @Query("SELECT * FROM UserTaskEntity WHERE userId = :userId AND completed IS NULL ORDER BY assigned ASC")
    fun getAllByUserIdAndPending(userId: String) : LiveData<List<UserTaskEntity>>
}



