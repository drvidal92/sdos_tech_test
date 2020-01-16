package es.uvigo.esei.drvidal.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import es.uvigo.esei.drvidal.entity.UserEntity

/**
 * Created by Denís Requejo on 2020-01-15.
 */
@Dao
interface UserDao: BaseDao<UserEntity>{

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(users: List<UserEntity>)

    @Query("SELECT * FROM UserEntity WHERE id = :id AND password = :password")
    fun getByIdAndPassword(id: String, password: String ): UserEntity?
}