package es.uvigo.esei.drvidal.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import es.uvigo.esei.drvidal.dao.*
import es.uvigo.esei.drvidal.entity.*

/**
 * Created by Denís Requejo on 2020-01-15.
 */
@Database(entities = [UserEntity::class, HabilityEntity::class, UserHabilityEntity::class, UserTaskEntity::class, WebServiceEntity::class], version = 1, exportSchema = false)
abstract class MyRoomDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun userHabilityDao(): UserHabilityDao
    abstract fun taskDao(): HabilityDao
    abstract fun userTaskDao(): UserTaskDao
    abstract fun webServiceDao(): WebServiceDao

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