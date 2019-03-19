package dev.sadewawicak.creativesolusindo.sismiopadmin.superadmin.menu.wp.jumlah

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter

import dev.sadewawicak.creativesolusindo.sismiopadmin.R
import dev.sadewawicak.creativesolusindo.sismiopadmin.client.APIResponse
import dev.sadewawicak.creativesolusindo.sismiopadmin.model.DataModel
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.find
import org.jetbrains.anko.info
import org.jetbrains.anko.support.v4.toast
import com.github.mikephil.charting.utils.ViewPortHandler
import com.github.mikephil.charting.formatter.IValueFormatter
import java.text.DecimalFormat

class JumlahWPSuperAdminFragment : Fragment(), AnkoLogger, JumlahWPSuperAdminView{

    override fun getJumlahWPKecamatan(totalwp: Float, totalwpbayar: Float) {
        dataJumlahWPBayar.add(totalwpbayar)
        dataJumlahWP.add(totalwp)
        info("${dataJumlahWP.size}")
        if (loop == 0){
            barChart.visibility = View.VISIBLE
        }
        setData(dataJumlahWP,dataJumlahWPBayar,loop, dataKecamatan?.get(loop)?.nAMAKEC.toString())
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
                superAdminPresenter.getJumlahSPPT(this.dataKecamatan?.get(i)?.kDKEC.toString())
            }
        }
    }

    private lateinit var superAdminPresenter: JumlahWPSuperAdminPresenter
    private var dataKecamatan: ArrayList<DataModel?>? = null
    private var dataWPKecamatan: ArrayList<DataModel?>? = null
    private var dataWPKecamatanDibayar: ArrayList<DataModel?>? = null
    private var dataJumlahWP: ArrayList<Float> = arrayListOf()
    private var dataJumlahWPBayar: ArrayList<Float> = arrayListOf()
    private lateinit var barChart: BarChart

    private lateinit var loading: ProgressBar
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_jumlah_wpsuper_admin, container, false)

        superAdminPresenter = JumlahWPSuperAdminPresenter(context, APIResponse().response(),this)

        barChart = view.find(R.id.chart_wp_superadmin)
        loading = view.find(R.id.loading)

        dataKecamatan = arrayListOf()

        dataWPKecamatan = arrayListOf()
        dataWPKecamatanDibayar = arrayListOf()
        dataJumlahWP = arrayListOf()
        dataJumlahWPBayar = arrayListOf()


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

    private fun setData(jumlahWP: ArrayList<Float>, jumlahWPBayar: ArrayList<Float>, i:Int, xValss:String) {
        //--------------------------------------------------
        val barWidth = 0.3f
        val barSpace = 0f
        val groupSpace = 0.4f

        val groupCount = i

        values1.add(BarEntry(i.toFloat(), (jumlahWPBayar.get(i)).toFloat()))
        values2.add(BarEntry(i.toFloat(), ((jumlahWP.get(i)-jumlahWPBayar.get(i))).toFloat()))
        xVals.add(xValss)

        val set1: BarDataSet
        val set2: BarDataSet

        set1 = BarDataSet(values1, "Sudah Bayar")
        set1.color = Color.rgb(104, 241, 175)
        set2 = BarDataSet(values2, "Belum Bayar")
        set2.color = Color.rgb(164, 228, 251)

        val data = BarData(set1,set2)

        data.setValueFormatter(RupiahFormatter())
        barChart.data = data
        barChart.barData.barWidth = barWidth
        barChart.xAxis.axisMinimum = 0f
        barChart.xAxis.axisMaximum = (10f + barChart.barData.getGroupWidth(groupSpace, barSpace) * groupCount)
        barChart.groupBars(0f, groupSpace, barSpace)
        barChart.data.isHighlightEnabled = false

        val legend = barChart.legend
        legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        legend.orientation = Legend.LegendOrientation.HORIZONTAL
        legend.setDrawInside(true)
        legend.yOffset = 60f
        legend.xOffset = 0f
        legend.isWordWrapEnabled = true
        legend.yEntrySpace = 0f
        legend.textSize = 14f

        barChart.invalidate()

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
        leftAxis.valueFormatter = RupiahFormatter()
        leftAxis.setDrawGridLines(true)
        leftAxis.spaceTop = 35f
        leftAxis.axisMinimum = 0f
    }

    inner class RupiahFormatter : IValueFormatter, IAxisValueFormatter {
        override fun getFormattedValue(value: Float, axis: AxisBase?): String {
            return " RP."+mFormat.format(value) // e.g. append a dollar-sign
        }
        override fun getFormattedValue(value: Float, entry: Entry?, dataSetIndex: Int, viewPortHandler: ViewPortHandler?): String {
            return " RP."+mFormat.format(value) // e.g. append a dollar-sign
        }
        private val mFormat: DecimalFormat
        init {
            mFormat = DecimalFormat("###,###,###,###.0") // use one decimal
        }
    }

}