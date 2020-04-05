package company.com.volve.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import company.com.volve.R;


@SuppressWarnings("ALL")
public class GreenTipsFragment extends android.support.v4.app.Fragment {

    Button Button1, Button2, Button3, Button4, Button5, Button6, Button7, Button8, Button9, Button10, Button11, Button12, Button13, Button14, Button15;
    private View view;
    TextView tips;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_green_tips, container, false);
        LinearLayout activityLayout = new LinearLayout(getActivity());
        tips = view.findViewById(R.id.tips_text_view);
        tips.setText(R.string.green_tips_text);
        tips.setTextSize(20);

        activityLayout.getRootView().setBackgroundColor(Color.CYAN);
        return view;
    }


}