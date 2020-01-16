package es.uvigo.esei.drvidal.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.io.Serializable

/**
 * Created by Denís Requejo on 2020-01-15.
 */
@Entity(indices = [Index(value = ["id"])])
data class UserEntity(@PrimaryKey val id: String, val password: String, val name: String, val code: String, val type: Int = TECHNICAL) : Serializable {
    companion object {
        const val ADMIN = 1
        const val TECHNICAL = 2
    }
}