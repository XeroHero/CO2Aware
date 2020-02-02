package tk.xerohero.co2aware.ui.greenTips;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.widget.TextViewCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.Objects;

import tk.xerohero.co2aware.R;

public class GreenTipsFragment extends Fragment {

    private GreenTipsViewModel greenTipsViewModel;

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);


        TextView textView = root.findViewById(R.id.green_tips_text_view);
        TextViewCompat.setAutoSizeTextTypeWithDefaults(textView, TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM);

        textView.setText("Hi hi hi");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            textView.setLineHeight(TextView.AUTO_SIZE_TEXT_TYPE_NONE);

        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            textView.setAutoSizeTextTypeUniformWithConfiguration(18, 32, 1, 1);
        } else textView.setTextSize(20);

        return root;



    }
}