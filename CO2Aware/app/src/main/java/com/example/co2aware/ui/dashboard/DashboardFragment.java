package com.example.co2aware.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.co2aware.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        ArrayList gasEmissions = new ArrayList();
        //TODO: Provide integration to populate arrayList with data
        constructPieChart(gasEmissions);

        return root;

    }

    private void constructPieChart(ArrayList gasEmissions) {
        gasEmissions.add(new PieEntry(945f, 2));
        gasEmissions.add(new PieEntry(1133f, 5));
        gasEmissions.add(new PieEntry(1578f, 92));

        PieDataSet dataSet = new PieDataSet(gasEmissions, "Emissions breakdown");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        //add dataset to the data model to be shown
        PieData data = new PieData(dataSet);
        //instantiate the pie chart on screen
        PieChart pieChart = new PieChart(getContext());

        pieChart.animateXY(5000, 5000);

        pieChart.setData(data);

    }
}