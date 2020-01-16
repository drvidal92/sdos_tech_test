package es.uvigo.esei.drvidal.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import es.uvigo.esei.drvidal.entity.UserEntity
import es.uvigo.esei.drvidal.entity.UserHabilityEntity

/**
 * Created by Denís Requejo on 2020-01-15.
 */
@Dao
interface UserHabilityDao: BaseDao<UserHabilityEntity> {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(userHabilities: List<UserHabilityEntity>)

    @Query("SELECT * FROM UserEntity UE JOIN UserHabilityEntity UHE ON UE.id = UHE.userId WHERE UHE.habilityId = :habilityId")
    fun getUsersByHability(habilityId: Int): List<UserEntity>
}