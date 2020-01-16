package es.uvigo.esei.drvidal.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

/**
 * Created by Denís Requejo on 2020-01-15.
 */
@Entity
data class HabilityEntity(@PrimaryKey val id: Int, val name: String) : Serializable
