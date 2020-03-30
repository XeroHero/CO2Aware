package tk.xerohero.co2aware.ui.PieChartFragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import tk.xerohero.co2aware.BikeListView;
import tk.xerohero.co2aware.R;


public class PieChartFragment extends Fragment {

    @NonNull
    public static Fragment newInstance() {
        return new PieChartFragment();
    }

    @SuppressWarnings("FieldCanBeLocal")
    private PieChart chart;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_simple_pie, container, false);

        chart = v.findViewById(R.id.pieChart1);
        chart.getDescription().setEnabled(false);

        chart.setCenterText(generateCenterText());
        chart.setCenterTextSize(10f);

        // radius of the center hole in percent of maximum radius
        chart.setHoleRadius(45f);
        chart.setTransparentCircleRadius(50f);

        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);

        chart.setData(generatePieData(4));

        return v;
    }

    private SpannableString generateCenterText() {
        SpannableString s = new SpannableString("Emission\nGases");
        s.setSpan(new RelativeSizeSpan(2f), 0, 8, 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), 8, s.length(), 0);
        return s;
    }


    /**
     * generates data (1 DataSet, 4 values)
     * @return container with PieData (data to be fed to constructor for the pie chart)
     */
    private PieData generatePieData(int numGases) {


        ArrayList<PieEntry> pollutantList = new ArrayList<>();

        for(int i = 0; i < numGases; i++) {
            pollutantList.add(new PieEntry((float) ((Math.random() * 60) + 40), "Gas " + (i+1)));
        }

        PieDataSet ds1 = new PieDataSet(pollutantList, "Emissions of pollutants caused by your commutes");
        ds1.setColors(ColorTemplate.VORDIPLOM_COLORS);
        ds1.setSliceSpace(2f);
        ds1.setValueTextColor(Color.WHITE);
        ds1.setValueTextSize(12f);

        return new PieData(ds1);
    }
}
