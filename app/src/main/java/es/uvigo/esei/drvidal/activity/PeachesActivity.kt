package es.uvigo.esei.drvidal.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import es.uvigo.esei.drvidal.R
import es.uvigo.esei.drvidal.adapter.WebServiceListAdapter
import es.uvigo.esei.drvidal.entity.WebServiceEntity
import es.uvigo.esei.drvidal.util.ioThread
import es.uvigo.esei.drvidal.viewmodel.WebServiceViewModel
import kotlinx.android.synthetic.main.recycler_view.*
import org.jetbrains.anko.indeterminateProgressDialog

/**
 * Created by Denís Requejo on 2020-01-20.
 */
class PeachesActivity : AppCompatActivity() {

    private lateinit var webServiceViewModel: WebServiceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recycler_view)
        val mProgressDialog = indeterminateProgressDialog("Recuperando items...")
        mProgressDialog.setCancelable(false)
        mProgressDialog.show()
        webServiceViewModel = ViewModelProvider(this).get(WebServiceViewModel::class.java)
        ioThread {
            val webServiceList = webServiceViewModel.getAll()
            runOnUiThread {
                webServiceList.observe(this, Observer { items ->
                    setAdapter(items)
                    mProgressDialog.dismiss()
                })
            }
        }
    }

    private fun setAdapter(items : List<WebServiceEntity>) {
        val adapter = WebServiceListAdapter(this)
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this)
        adapter.setItems(items)
    }

}

