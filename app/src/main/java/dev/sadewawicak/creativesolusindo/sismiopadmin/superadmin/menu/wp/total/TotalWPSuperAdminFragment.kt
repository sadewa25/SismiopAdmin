package dev.sadewawicak.creativesolusindo.sismiopadmin.superadmin.menu.wp.total


import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.LargeValueFormatter

import dev.sadewawicak.creativesolusindo.sismiopadmin.R
import dev.sadewawicak.creativesolusindo.sismiopadmin.client.APIResponse
import dev.sadewawicak.creativesolusindo.sismiopadmin.model.DataModel
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.find
import org.jetbrains.anko.info
import org.jetbrains.anko.support.v4.toast


class TotalWPSuperAdminFragment : Fragment(), TotalWPSuperAdminView, AnkoLogger{

    override fun getTotalWPKecamatan(totalwp: Float, totalwpbayar: Float) {
        dataTotalWPBayar.add(totalwpbayar)
        dataTotalWP.add(totalwp)
        info("${dataTotalWP.size}")
        if (loop == 0){
            barChart.visibility = View.VISIBLE
        }
        setData(dataTotalWP,dataTotalWPBayar,loop, dataKecamatan?.get(loop)?.nAMAKEC.toString())
        loop += 1
    }

    override fun hideLoading() {
        loading.visibility = View.INVISIBLE
    }
    override fun showLoading() {
        loading.visibility = View.VISIBLE
    }
    override fun getResponse(text: String) {
        toast(text)
    }

    override fun getDataKecamatan(dataKecamatan: List<DataModel?>?) {
        if (dataKecamatan != null){
            this.dataKecamatan?.addAll(dataKecamatan)
            for (i in 0..dataKecamatan.size-1){
                superAdminPresenter.getTotalWP(this.dataKecamatan?.get(i)?.kDKEC.toString())
            }
        }
    }

    private lateinit var superAdminPresenter: TotalWPSuperAdminPresenter
    private var dataKecamatan: ArrayList<DataModel?>? = null
    private var dataWPKecamatan: ArrayList<DataModel?>? = null
    private var dataWPKecamatanDibayar: ArrayList<DataModel?>? = null
    private var dataTotalWP: ArrayList<Float> = arrayListOf()
    private var dataTotalWPBayar: ArrayList<Float> = arrayListOf()
    private lateinit var barChart: BarChart

    private lateinit var loading: ProgressBar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_total_wpsuper_admin, container, false)

        superAdminPresenter = TotalWPSuperAdminPresenter(context, APIResponse().response(),this)

        barChart = view.find(R.id.chart_wp)
        loading = view.find(R.id.loading)

        dataKecamatan = arrayListOf()

        dataWPKecamatan = arrayListOf()
        dataWPKecamatanDibayar = arrayListOf()
        dataTotalWP = arrayListOf()
        dataTotalWPBayar = arrayListOf()

        barChart.description = null
        barChart.setPinchZoom(true)
        barChart.setScaleEnabled(true)
        barChart.setDrawBarShadow(false)
        barChart.setDrawGridBackground(false)

        //Taruh Chartnya
        superAdminPresenter.getDataKecamatan()

        return view
    }


    val values1 = ArrayList<BarEntry>() //SPPT
    val values2 = ArrayList<BarEntry>() //SPPT Bayar
    var xVals:ArrayList<String> = arrayListOf()
    var loop:Int = 0

    private fun setData(totalWP: ArrayList<Float>, totalWPBayar: ArrayList<Float>, i:Int, xValss:String) {
        //--------------------------------------------------
        val barWidth = 0.3f
        val barSpace = 0f
        val groupSpace = 0.4f
        // (0.2 + 0.03) * 4 + 0.08 = 1.00 -> interval per "group"

        val groupCount = i

        values1.add(BarEntry(i.toFloat(), (totalWPBayar.get(i)).toFloat()))
        values2.add(BarEntry(i.toFloat(), ((totalWP.get(i)-totalWPBayar.get(i))).toFloat()))
        xVals.add(xValss)

        val set1: BarDataSet
        val set2: BarDataSet

        set1 = BarDataSet(values1, "Sudah Membayar")
        set1.color = Color.rgb(104, 241, 175)
        set2 = BarDataSet(values2, "Belum Membayar")
        set2.color = Color.rgb(164, 228, 251)

        val data = BarData(set1,set2)

        data.setValueFormatter(LargeValueFormatter())
        barChart.data = data
        barChart.barData.barWidth = barWidth
        barChart.xAxis.axisMinimum = 0f
        barChart.xAxis.axisMaximum = (10f + barChart.barData.getGroupWidth(groupSpace, barSpace) * groupCount)
        barChart.groupBars(0f, groupSpace, barSpace)
        barChart.data.isHighlightEnabled = false
        barChart.invalidate()

        val legend = barChart.legend
        legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        legend.orientation = Legend.LegendOrientation.HORIZONTAL
        legend.setDrawInside(true)
        legend.yOffset = 60f
        legend.xOffset = 0f
        legend.yEntrySpace = 0f
        legend.textSize = 10f

        //X-axis
        val xAxis = barChart.xAxis
        xAxis.granularity = 1f
        xAxis.isGranularityEnabled = true
        xAxis.setCenterAxisLabels(true)
        xAxis.setDrawGridLines(false)
        xAxis.axisMaximum = 18f
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.valueFormatter = IndexAxisValueFormatter(xVals)
        //Y-axis
        barChart.axisRight.isEnabled = false
        val leftAxis = barChart.axisLeft
        leftAxis.valueFormatter = LargeValueFormatter()
        leftAxis.setDrawGridLines(true)
        leftAxis.spaceTop = 35f
        leftAxis.axisMinimum = 0f
    }

}