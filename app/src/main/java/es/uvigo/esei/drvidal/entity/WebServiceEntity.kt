package es.uvigo.esei.drvidal.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Denís Requejo on 2020-01-20.
 */

@Entity
data class WebServiceEntity(@PrimaryKey val farmerId: Int, val farmName: String, val category : String, val zipcode: String)