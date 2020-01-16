package es.uvigo.esei.drvidal.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy

/**
 * Created by Denís Requejo on 2020-01-15.
 */

interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(vararg obj: T)
}