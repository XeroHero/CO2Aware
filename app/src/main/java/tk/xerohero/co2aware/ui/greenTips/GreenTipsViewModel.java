package tk.xerohero.co2aware.ui.greenTips;

import android.widget.TextView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GreenTipsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    private TextView textView;
    public GreenTipsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Some green tips to make your life more eco-friendly!");

    }

    public LiveData<String> getText() {
        return mText;
    }
}