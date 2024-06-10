package com.modul.buahhati.view.view_chart

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.anychart.AnyChart
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.charts.Cartesian
import com.anychart.core.cartesian.series.Area
import com.anychart.data.Set
import com.anychart.enums.MarkerType
import com.modul.buahhati.databinding.ActivityViewChartBinding

class ViewChartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewChartBinding
    private lateinit var titleChart :TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewChartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupChart()
    }

    private fun setupChart(){

//        val bundle = intent.extras
//        val viewTinggiBadan = bundle!!.getBoolean(tinggiBadan)
//        val viewBeratBadan = bundle!!.getBoolean(beratBadan)
//        val viewLingkarKepala = bundle!!.getBoolean(lingkarKepala)


        titleChart = binding.titleStat
        // Inisialisasi AnyChartView
        val anyChartView = binding.lineChart

        // Inisialisasi Line Chart
        val lineChart = AnyChart.line()

        lineChart.padding(10.0, 20.0, 5.0, 20.0);
        lineChart.yAxis(0).title("Tinggi (cm)");
        lineChart.xAxis(0).title("Usia (bulan)");

        // Menambahkan konfigurasi grid ke chart
        lineChart.xGrid(true)
        lineChart.yGrid(true)

        var benchmarkData: List<DataEntry> = ArrayList()
        var seriesData: List<DataEntry> = ArrayList()

//        if (viewBeratBadan == true){
            benchmarkData += CustomDataEntry("0", 4.4, 2.5)
            benchmarkData += CustomDataEntry("1", 5.8, 3.4)
            benchmarkData += CustomDataEntry("2", 7.1, 4.3)
            benchmarkData += CustomDataEntry("3", 8.0, 5.0)
            benchmarkData += CustomDataEntry("4", 8.7, 5.6)
            benchmarkData += CustomDataEntry("5", 9.3, 6.0)
            benchmarkData += CustomDataEntry("6", 9.8, 6.4)
            benchmarkData += CustomDataEntry("7", 10.3, 6.7)
            benchmarkData += CustomDataEntry("8", 10.7, 6.9)
            benchmarkData += CustomDataEntry("9", 11.0, 7.1)
            benchmarkData += CustomDataEntry("10", 11.4, 7.4)
            benchmarkData += CustomDataEntry("11", 11.7, 7.6)
            benchmarkData += CustomDataEntry("12", 12.0, 7.7)
            benchmarkData += CustomDataEntry("13", 12.3, 7.9)
            benchmarkData += CustomDataEntry("14", 12.6, 8.1)
            benchmarkData += CustomDataEntry("15", 12.8, 8.3)
            benchmarkData += CustomDataEntry("16", 13.1, 8.4)
            benchmarkData += CustomDataEntry("17", 13.4, 8.6)
            benchmarkData += CustomDataEntry("18", 13.7, 8.8)
            benchmarkData += CustomDataEntry("19", 13.9, 8.9)
            benchmarkData += CustomDataEntry("20", 14.2, 9.1)
            benchmarkData += CustomDataEntry("21", 14.5, 9.2)
            benchmarkData += CustomDataEntry("22", 14.7, 9.4)
            benchmarkData += CustomDataEntry("23", 15.0, 9.5)
            benchmarkData += CustomDataEntry("24", 15.5, 9.7)
            benchmarkData += CustomDataEntry("25", 15.6, 9.8)
            benchmarkData += CustomDataEntry("26", 15.8, 10.0)
            benchmarkData += CustomDataEntry("27", 16.1, 10.1)
            benchmarkData += CustomDataEntry("28", 16.4, 10.2)
            benchmarkData += CustomDataEntry("29", 16.6, 10.4)
            benchmarkData += CustomDataEntry("30", 16.8, 10.5)
            benchmarkData += CustomDataEntry("31", 17.1, 10.7)
            benchmarkData += CustomDataEntry("32", 17.4, 10.8)
            benchmarkData += CustomDataEntry("33", 17.6, 10.9)
            benchmarkData += CustomDataEntry("34", 17.8, 11.0)
            benchmarkData += CustomDataEntry("35", 18.1, 11.2)
            benchmarkData += CustomDataEntry("36", 18.3, 11.3)



//        }else if(viewLingkarKepala == true){
//            binding.titleStat.setText("Lingkar Kepala")
//        }else{
//            binding.titleStat.setText("Tinggi Badan")
//            benchmarkData += CustomDataEntry("0", 53.69, 46.09)
//            benchmarkData += CustomDataEntry("1", 58.59, 50.79)
//            benchmarkData += CustomDataEntry("2", 62.39, 54.39)
//            benchmarkData += CustomDataEntry("3", 65.49, 57.29)
//            benchmarkData += CustomDataEntry("4", 67.99, 59.69)
//            benchmarkData += CustomDataEntry("5", 70.09, 61.69)
//            benchmarkData += CustomDataEntry("6", 71.89, 63.29)
//            benchmarkData += CustomDataEntry("7", 73.49, 64.79)
//            benchmarkData += CustomDataEntry("8", 74.99, 66.19)
//            benchmarkData += CustomDataEntry("9", 76.49, 67.49)
//            benchmarkData += CustomDataEntry("10", 77.89, 68.69)
//            benchmarkData += CustomDataEntry("11", 79.19, 69.89)
//            benchmarkData += CustomDataEntry("12", 80.49, 70.99)
//            benchmarkData += CustomDataEntry("13", 81.79, 72.09)
//            benchmarkData += CustomDataEntry("14", 82.99, 73.09)
//            benchmarkData += CustomDataEntry("15", 84.19, 74.09)
//            benchmarkData += CustomDataEntry("16", 85.39, 74.99)
//            benchmarkData += CustomDataEntry("17", 86.49, 75.99)
//            benchmarkData += CustomDataEntry("18", 87.69, 76.89)
//            benchmarkData += CustomDataEntry("19", 88.79, 77.69)
//            benchmarkData += CustomDataEntry("20", 89.79, 78.59)
//            benchmarkData += CustomDataEntry("21", 90.89, 79.39)
//            benchmarkData += CustomDataEntry("22", 91.89, 80.19)
//            benchmarkData += CustomDataEntry("23", 92.89, 80.99)
//            benchmarkData += CustomDataEntry("24", 93.89, 81.69)
//        }

        val set = Set.instantiate()
        set.data(benchmarkData)
        val series1TB = set.mapAs("{ x: 'x', value: 'value' }")
        val series2TB = set.mapAs("{ x: 'x', value: 'value2' }")


        val areaChart: Cartesian = AnyChart.area()
        val series1: Area = areaChart.area(series1TB)
        series1.name("Ideal")
        series1.normal().fill("#b8e4a4", 1);
        series1.hovered().fill("#b8e4a4", 1);
        series1.selected().fill("#b8e4a4", 1);
        series1.normal().stroke("3 #dec250")
        series1.selected().stroke("3 #dec250")
        series1.hovered().stroke("3 #dec250")
        series1.hovered().markers().enabled(true)
        series1.hovered().markers()
            .type(MarkerType.CIRCLE)
            .size(4.0)
            .stroke("1.5 #dec250")
        series1.markers().zIndex(100.0)




        val series2: Area = areaChart.area(series2TB)
        series2.name("Tidak Ideal")
        series2.normal().fill("#ffffff", 1);
        series2.hovered().fill("#ffffff", 1);
        series2.selected().fill("#ffffff", 1);
        series2.stroke("3 #dec250")
        series2.normal().stroke("3 #dec250")
        series2.selected().stroke("3 #dec250")
        series2.hovered().stroke("3 #dec250")
        series2.hovered().markers().enabled(true)
        series2.hovered().markers()
            .type(MarkerType.CIRCLE)
            .size(4.0)
            .stroke("1.5 #fff")
        series2.markers().zIndex(100.0)

        anyChartView.setChart(areaChart)
    }

    private class CustomDataEntry internal constructor(
        x: String?,
        value: Double?,
        value2: Double?

    ) :
        ValueDataEntry(x, value) {
        init {
            setValue("value2", value2)
        }
    }
}