package es.uvigo.esei.drvidal.entity

import androidx.room.Entity
import androidx.room.ForeignKey

/**
 * Created by Denís Requejo on 2020-01-15.
 */
@Entity(primaryKeys = ["userId", "habilityId", "assigned"],
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
data class UserTaskEntity(
    val userId: String,
    val habilityId: Int,
    val description: String,
    val assigned: Long,
    val minutesDuration: Int,
    val completed: Long? = null
)