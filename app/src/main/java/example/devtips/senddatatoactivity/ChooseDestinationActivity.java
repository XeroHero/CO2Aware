package example.devtips.senddatatoactivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.net.MalformedURLException;
import java.net.URL;

import es.dmoral.toasty.Toasty;

import static es.dmoral.toasty.Toasty.warning;
import static example.devtips.senddatatoactivity.R.string.try_again_desstination_empty;
import static example.devtips.senddatatoactivity.R.string.try_again_source;
import static example.devtips.senddatatoactivity.R.string.try_again_transport_2;
import static example.devtips.senddatatoactivity.R.string.try_again_transport_empty;

public class ChooseDestinationActivity extends AppCompatActivity {
    static String origin;
    static String destination;

    @SuppressLint({"ClickableViewAccessibility", "CheckResult"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_destination);

        Button bikeStationBrowser = findViewById(R.id.view_itineraries_button);
        bikeStationBrowser.setOnClickListener(v -> {
            startActivity(new Intent(ChooseDestinationActivity.this,
                    BikeStationBrowser.class));
        });

        EditText originTextbox = findViewById(R.id.origin_textbox);

        EditText destinationTextbox = findViewById(R.id.destination_textbox);

        Button viewItineraries = findViewById(R.id.search_button);
        viewItineraries.setOnClickListener(v -> {
            origin = originTextbox.getText().toString();
            destination = destinationTextbox.getText().toString();

            String url = "https://www.google.com/maps/dir/" + origin + "/" + destination;
            URL link = null;
            try {
                link = new URL(url);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(String.valueOf(link)));
            startActivity(i);
        });
    }
}

