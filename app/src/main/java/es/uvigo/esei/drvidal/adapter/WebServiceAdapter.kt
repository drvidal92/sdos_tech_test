package es.uvigo.esei.drvidal.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import es.uvigo.esei.drvidal.R
import es.uvigo.esei.drvidal.entity.WebServiceEntity


/**
 * Created by Denís Requejo on 2019-10-30.
 */
class WebServiceListAdapter internal constructor(context: Context) :
    RecyclerView.Adapter<WebServiceListAdapter.WebServiceViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var items = emptyList<WebServiceEntity>()

    inner class WebServiceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textName: TextView = itemView.findViewById(R.id.text_farm_name)
        val textId: TextView = itemView.findViewById(R.id.text_farm_id)
        val textCategory: TextView = itemView.findViewById(R.id.category)
        val textZipCode: TextView = itemView.findViewById(R.id.text_zip_code)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WebServiceViewHolder {
        val itemView = inflater.inflate(R.layout.item_webservice , parent, false)
        return WebServiceViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WebServiceViewHolder, position: Int) {
        val current = items[position]
        holder.textName.text = "Nombre: " + current.farmName
        holder.textId.text = "Id: " + current.farmerId
        holder.textCategory.text = "Categoría: " + current.category
        holder.textZipCode.text = "ZIP: " + current.zipcode

    }

    internal fun setItems(items: List<WebServiceEntity>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun getItemCount() = items.size

}