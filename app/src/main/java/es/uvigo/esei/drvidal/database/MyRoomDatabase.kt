package es.uvigo.esei.drvidal.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import es.uvigo.esei.drvidal.dao.HabilityDao
import es.uvigo.esei.drvidal.dao.UserDao
import es.uvigo.esei.drvidal.dao.UserHabilityDao
import es.uvigo.esei.drvidal.dao.UserTaskDao
import es.uvigo.esei.drvidal.entity.HabilityEntity
import es.uvigo.esei.drvidal.entity.UserEntity
import es.uvigo.esei.drvidal.entity.UserHabilityEntity
import es.uvigo.esei.drvidal.entity.UserTaskEntity

/**
 * Created by Denís Requejo on 2020-01-15.
 */
@Database(entities = [UserEntity::class, HabilityEntity::class, UserHabilityEntity::class, UserTaskEntity::class], version = 10, exportSchema = false)
abstract class MyRoomDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun userHabilityDao(): UserHabilityDao
    abstract fun taskDao(): HabilityDao
    abstract fun userTaskDao(): UserTaskDao

    companion object {
        @Volatile
        private var INSTANCE: MyRoomDatabase? = null

        fun getInstance(context: Context): MyRoomDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                MyRoomDatabase::class.java, "SDOSRoomDatabase"
            )
                //.allowMainThreadQueries() //only for technical purposes
                .fallbackToDestructiveMigration()
                .build()
    }
}