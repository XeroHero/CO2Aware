package tk.xerohero.co2aware.ui.greenTips;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.widget.TextViewCompat;
import androidx.fragment.app.Fragment;

import tk.xerohero.co2aware.R;

public class GreenTipsFragment extends Fragment {

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);


        TextView textView = root.findViewById(R.id.green_tips_tview);
        TextViewCompat.setAutoSizeTextTypeWithDefaults(textView, TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM);
        TextView greenTipsTitle = root.findViewById(R.id.greenTipsTitle);
        greenTipsTitle.setText("Here we present some handy tips to make your day a little greener!");
        greenTipsTitle.setTypeface(Typeface.DEFAULT_BOLD);
        textView.setMovementMethod(new ScrollingMovementMethod());
        greenTipsTitle.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        TextViewCompat.setAutoSizeTextTypeWithDefaults(greenTipsTitle, TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM);

        textView.setText(" • Take short showers in the morning, instead of baths in the evening. It saves up to 70% water.\n'" +
                " • Use rainwater to wash the car. Not only is it good for the environment, but also more pure than tap water\n" +
                " • Turn off the standby lights on your computer. These can use up to 6kW each year in power\n" +
                " • Take public transport that can avail of preferential bus/taxi lanes to reach your destination faster\n" +
                " • Choose an electric car as your next car. Did you know that travelling from Dublin to Donegal and back will cost you" +
                " under €8 with an electric vehicle\n" +
                " • Use energy-saving bulbs. They are not only good for the environment, but also for your wallet!\n" +
                " • Lower the heating in your house, and wear more clothes. It's better for the environment and lowers your heating bill!\n" +
                " • Share these tips with friends, family and colleagues.\n" +
                "bklabfiol;snk");


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            textView.setLineHeight(TextView.AUTO_SIZE_TEXT_TYPE_NONE);

        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            greenTipsTitle.setAutoSizeTextTypeUniformWithConfiguration(18, 40, 1,1);
            textView.setAutoSizeTextTypeUniformWithConfiguration(18, 32, 1, 1);
        } else textView.setTextSize(20);

        return root;


    }
}