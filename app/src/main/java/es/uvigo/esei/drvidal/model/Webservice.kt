package es.uvigo.esei.drvidal.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName




/**
 * Created by Denís Requejo on 2020-01-20.
 */

class Webservice {
    @SerializedName("farm_name")
    @Expose
    private var farmName: String = ""
    @SerializedName("category")
    @Expose
    private var category: String = ""
    @SerializedName("item")
    @Expose
    private var item:  String? = null
    @SerializedName("farmer_id")
    @Expose
    private var farmerId: Int = 0
    @SerializedName("website")
    @Expose
    private var website: Website? = null
    @SerializedName("zipcode")
    @Expose
    private var zipcode: String = ""
    @SerializedName("phone1")
    @Expose
    private var phone1: String? = null
    @SerializedName("business")
    @Expose
    private var business: String? = null
    @SerializedName("l")
    @Expose
    private var l: String? = null
    @SerializedName("location_1")
    @Expose
    private var location1: Location1? = null

    fun getFarmName(): String {
        return farmName
    }

    fun setFarmName(farmName: String) {
        this.farmName = farmName
    }

    fun getCategory(): String {
        return category
    }

    fun setCategory(category: String) {
        this.category = category
    }

    fun getItem(): String? {
        return item
    }

    fun setItem(item: String?) {
        this.item = item
    }

    fun getFarmerId(): Int {
        return farmerId
    }

    fun setFarmerId(farmerId: Int) {
        this.farmerId = farmerId
    }

    fun getWebsite(): Website? {
        return website
    }

    fun setWebsite(website: Website?) {
        this.website = website
    }

    fun getZipcode(): String {
        return zipcode
    }

    fun setZipcode(zipcode: String) {
        this.zipcode = zipcode
    }

    fun getPhone1(): String? {
        return phone1
    }

    fun setPhone1(phone1: String?) {
        this.phone1 = phone1
    }

    fun getBusiness(): String? {
        return business
    }

    fun setBusiness(business: String?) {
        this.business = business
    }

    fun getL(): String? {
        return l
    }

    fun setL(l: String?) {
        this.l = l
    }

    fun getLocation1(): Location1? {
        return location1
    }

    fun setLocation1(location1: Location1?) {
        this.location1 = location1
    }
}