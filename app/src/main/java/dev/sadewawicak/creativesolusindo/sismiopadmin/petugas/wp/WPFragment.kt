package dev.sadewawicak.creativesolusindo.sismiopadmin.petugas.wp


import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.MPPointF

import dev.sadewawicak.creativesolusindo.sismiopadmin.R
import dev.sadewawicak.creativesolusindo.sismiopadmin.client.APIResponse
import dev.sadewawicak.creativesolusindo.sismiopadmin.session.SessionManager
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.find


class WPFragment : Fragment(),WPView, AnkoLogger{

    override fun showLoading() {
        loading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        loading.visibility = View.INVISIBLE
    }

    override fun getWP(totalwp: Float, totalwpbayar: Float) {
        setData(totalwp,totalwpbayar)
    }

    private lateinit var chartSPPT: PieChart
    private lateinit var presenter:WPPresenter
    private lateinit var sessionManager: SessionManager
    private lateinit var loading:ProgressBar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_w, container, false)

        chartSPPT = view.find(R.id.chart_wp)
        loading = view.find(R.id.loading)

        presenter = WPPresenter(context, APIResponse().response(),this)
        sessionManager = SessionManager(context)

        chartSPPT.setUsePercentValues(true)
        chartSPPT.setExtraOffsets(5f,10f,5f,5f)

        chartSPPT.dragDecelerationFrictionCoef = 0.95f
        chartSPPT.description.isEnabled = false
        chartSPPT.isDrawHoleEnabled = true
        chartSPPT.setHoleColor(Color.WHITE)
        chartSPPT.setTransparentCircleColor(Color.WHITE)
        chartSPPT.setTransparentCircleAlpha(110)

        chartSPPT.holeRadius = 58f
        chartSPPT.transparentCircleRadius = 61f
        chartSPPT.setDrawCenterText(true)
        chartSPPT.rotationAngle = 0f

        chartSPPT.isRotationEnabled = true
        chartSPPT.isHighlightPerTapEnabled = true

        chartSPPT.animateY(1400, Easing.EaseInOutQuad)

        val l = chartSPPT.legend
        l.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        l.horizontalAlignment  = Legend.LegendHorizontalAlignment.RIGHT
        l.orientation = Legend.LegendOrientation.VERTICAL
        l.setDrawInside(false)
        l.xEntrySpace = 7f
        l.yEntrySpace = 0f
        l.yOffset = 0f

        presenter.getTotalWP("3526${sessionManager.getKDKec()}${sessionManager.getKDKel()}")

        return view
    }

    private fun setData(totalWP: Float, totalWPBayar: Float) {
        val entries = arrayListOf<PieEntry>()

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        entries.add(
            PieEntry(
                ((totalWPBayar/totalWP)*100).toFloat(),
                "Sudah Bayar"
            )
        )

        entries.add(
            PieEntry(
                (((totalWP -totalWPBayar)/ totalWP)*100).toFloat(),
                "Belum Bayar"
            )
        )

        val dataSet = PieDataSet(entries, "Informasi Legenda")

        dataSet.setDrawIcons(false)

        dataSet.sliceSpace = 3f
        dataSet.iconsOffset = MPPointF(0f, 40f)
        dataSet.selectionShift = 5f

        // add a lot of colors

        val colors = arrayListOf<Int>()

        for (c in ColorTemplate.COLORFUL_COLORS)
            colors.add(c)

        for (c in ColorTemplate.LIBERTY_COLORS)
            colors.add(c)

        colors.add(ColorTemplate.getHoloBlue())

        dataSet.setColors(colors)

        val data = PieData(dataSet)
        data.setValueTextSize(11f)
        data.setValueTextColor(Color.WHITE)
        chartSPPT.data = data

        // undo all highlights
        chartSPPT.highlightValue(null)

        chartSPPT.invalidate()
    }

    override fun onResume() {
        super.onResume()
    }

}
