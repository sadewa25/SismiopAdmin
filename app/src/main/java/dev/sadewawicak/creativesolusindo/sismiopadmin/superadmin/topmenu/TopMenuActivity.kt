package dev.sadewawicak.creativesolusindo.sismiopadmin.superadmin.topmenu

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import dev.sadewawicak.creativesolusindo.sismiopadmin.R
import dev.sadewawicak.creativesolusindo.sismiopadmin.model.DataModel
import dev.sadewawicak.creativesolusindo.sismiopadmin.superadmin.bphtb.MainBPHTBSuperAdminActivity
import dev.sadewawicak.creativesolusindo.sismiopadmin.superadmin.pbb.MainActivitySuperAdmin
import dev.sadewawicak.creativesolusindo.sismiopadmin.superadmin.topmenu.adapter.AdapterTopMainMenu
import kotlinx.android.synthetic.main.activity_top_menu.*
import org.jetbrains.anko.startActivity

class TopMenuActivity : AppCompatActivity() {

    private var dataItems:ArrayList<DataModel?>? = null
    private var adapterTopMainMenu: AdapterTopMainMenu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_menu)

        dataItems = arrayListOf()
        dataItems?.add(DataModel(itemImg = R.drawable.ic_pbb,itemText = getString(R.string.title_pbb)))
        dataItems?.add(DataModel(itemImg = R.drawable.ic_bphtb,itemText = getString(R.string.title_bphtb)))
        dataItems?.add(DataModel(itemImg = R.drawable.ic_profile,itemText = getString(R.string.title_profile)))

        adapterTopMainMenu = AdapterTopMainMenu(dataItems,applicationContext,{
            if (it.itemText.equals(getString(R.string.title_pbb)))
                startActivity<MainActivitySuperAdmin>()
            else if (it.itemText.equals(getString(R.string.title_bphtb)))
                startActivity<MainBPHTBSuperAdminActivity>()
            else if (it.itemText.equals(getString(R.string.title_profile)))
                startActivity<ProfileActivity>()
        })

        recycler_top_menu.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
        recycler_top_menu.adapter = adapterTopMainMenu
    }
}
