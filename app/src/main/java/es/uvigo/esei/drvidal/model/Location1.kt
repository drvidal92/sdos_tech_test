package es.uvigo.esei.drvidal.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




/**
 * Created by Denís Requejo on 2020-01-20.
 */

class Location1 {
    @SerializedName("latitude")
    @Expose
    private var latitude: String? = null
    @SerializedName("longitude")
    @Expose
    private var longitude: String? = null
    @SerializedName("human_address")
    @Expose
    private var humanAddress: String? = null

    fun getLatitude(): String? {
        return latitude
    }

    fun setLatitude(latitude: String?) {
        this.latitude = latitude
    }

    fun getLongitude(): String? {
        return longitude
    }

    fun setLongitude(longitude: String?) {
        this.longitude = longitude
    }

    fun getHumanAddress(): String? {
        return humanAddress
    }

    fun setHumanAddress(humanAddress: String?) {
        this.humanAddress = humanAddress
    }
}