package dev.sadewawicak.creativesolusindo.sismiopadmin.superadmin.menu.sppt

import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.github.mikephil.charting.charts.BarChart

import dev.sadewawicak.creativesolusindo.sismiopadmin.R
import dev.sadewawicak.creativesolusindo.sismiopadmin.client.APIResponse
import dev.sadewawicak.creativesolusindo.sismiopadmin.model.DataModel
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.find
import com.github.mikephil.charting.components.Legend.LegendForm
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.formatter.LargeValueFormatter
import org.jetbrains.anko.support.v4.toast
import kotlin.collections.ArrayList
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import org.jetbrains.anko.info

class SPPTSuperAdminFragment : Fragment(),AnkoLogger{

    private lateinit var presenter:SPPTSuperAdminPresenter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_spptsuper_admin, container, false)

        presenter = SPPTSuperAdminPresenter(context)

        val pager: ViewPager = view.find(R.id.viewpager_home)
        val tabs: TabLayout = view.find(R.id.tabs_home)

        presenter.setupViewPager(pager,fragmentManager)
        tabs.setupWithViewPager(pager)

        return view
    }

}