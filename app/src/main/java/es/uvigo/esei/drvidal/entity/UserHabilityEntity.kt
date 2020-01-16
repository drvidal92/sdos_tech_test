package es.uvigo.esei.drvidal.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import java.io.Serializable

/**
 * Created by Denís Requejo on 2020-01-16.
 */
@Entity(primaryKeys = ["userId", "habilityId"],
    foreignKeys = [ForeignKey(
        entity = UserEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("userId"),
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE

    ), ForeignKey(entity = HabilityEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("habilityId"),
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE
    )])
data class UserHabilityEntity(val userId: String,
                              val habilityId: Int) : Serializable
