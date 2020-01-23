package com.example.co2aware;

import android.os.Bundle;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class EmissionSummaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emission_summary);
        BottomNavigationView navView = findViewById(R.id.nav_view);
//         Passing each menu ID as a set of Ids because each
//         menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_dashboard)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        ArrayList gasEmissions = new ArrayList();

        //TODO: Provide integration to populate arrayList with data
        constructPieChart(gasEmissions);

    }

    private void constructPieChart(ArrayList gasEmissions) {
        gasEmissions.add(new BarEntry(945f, 2));
        gasEmissions.add(new BarEntry(1133f, 5));
        gasEmissions.add(new BarEntry(1578f, 92));

        PieDataSet dataSet = new PieDataSet(gasEmissions, "Emissions breakdown");

        //add dataset to the data model to be shown
        PieData data = new PieData(dataSet);
        //instantiate the pie chart on screen
        PieChart pieChart = findViewById(R.id.piechart);
        pieChart.setData(data);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS );

        pieChart.animateXY(5000, 5000);
    }

}
