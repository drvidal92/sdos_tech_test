package es.uvigo.esei.drvidal.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




/**
 * Created by Denís Requejo on 2020-01-20.
 */
class Website {
    @SerializedName("url")
    @Expose
    private var url: String? = null

    fun getUrl(): String? {
        return url
    }

    fun setUrl(url: String?) {
        this.url = url
    }
}