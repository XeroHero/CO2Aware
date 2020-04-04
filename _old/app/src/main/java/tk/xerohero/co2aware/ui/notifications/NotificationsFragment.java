package tk.xerohero.co2aware.ui.notifications;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;

import tk.xerohero.co2aware.R;

public class NotificationsFragment extends Fragment {

    private final String JCDecauxApiKey = "17566ea530bc36033158dda33346ffbc2313d405";
    private NotificationsViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        final TextView textView = root.findViewById(R.id.text_notifications);

        Ion.with(getContext())
                .load("https://api.jcdecaux.com/vls/v1/stations?contract=dublin&apiKey=" + JCDecauxApiKey)
                .asString()
                .withResponse()
                .setCallback(new FutureCallback<Response<String>>() {
                    @Override
                    public void onCompleted(Exception e, final Response<String> result) {
                        // print the response code, ie, 200
                        Log.i("RESC ", String.valueOf(result.getHeaders().code()));
                        //noinspection ConstantConditions
                        NotificationsViewModel.mText.postValue(result.getResult());//                        notificationsViewModel.getText().observe(null, new Observer<String>() {
                    }
                });
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);

        return root;
    }
}