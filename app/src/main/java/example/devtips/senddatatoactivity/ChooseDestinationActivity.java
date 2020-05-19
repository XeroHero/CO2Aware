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

import es.dmoral.toasty.Toasty;

import static es.dmoral.toasty.Toasty.warning;
import static example.devtips.senddatatoactivity.R.string.try_again_desstination_empty;
import static example.devtips.senddatatoactivity.R.string.try_again_source;
import static example.devtips.senddatatoactivity.R.string.try_again_transport_2;
import static example.devtips.senddatatoactivity.R.string.try_again_transport_empty;

public class ChooseDestinationActivity extends AppCompatActivity {
    static String origin;
    static String destination;
    static String transportType;
    Button bus, walk, drive, bike;
    private Button bikeStationBrowser, viewItineraries;

    @SuppressLint({"ClickableViewAccessibility", "CheckResult"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_destination);

        EditText originTextbox = findViewById(R.id.origin_textbox);
        origin = originTextbox.getText().toString();

        EditText destinationTextbox = findViewById(R.id.destination_textbox);
        destination = destinationTextbox.getText().toString();

        transitTypeSelector();

        bikeStationBrowser = findViewById(R.id.search_button);
        bikeStationBrowser.setOnClickListener(v -> {
            startActivity(new Intent(ChooseDestinationActivity.this,
                    BikeStationBrowser.class));
        });

        viewItineraries = findViewById(R.id.view_itineraries_button);
        viewItineraries.setOnClickListener(v -> {


            if (TextUtils.isEmpty(origin)) {
                warning(this, try_again_source,
                        Toasty.LENGTH_LONG);
            } else if (TextUtils.isEmpty(destination)) {
                warning(this, try_again_desstination_empty,
                        Toasty.LENGTH_LONG);
            } else if (TextUtils.isEmpty(transportType)) {
                warning(this, getString(try_again_transport_empty) +
                        getString(try_again_transport_2), Toasty.LENGTH_LONG);
            } else { //construct the intent URL
                String url = constructUrlQuery(origin, destination);

                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            }
        });
    }

    private String constructUrlQuery(String source, String destination) {
        return "http://maps.google.com/maps?saddr=" +
                source +
                "&daddr=" +
                destination +
                "&dirflg=" +
                transportType;
    }

    @SuppressLint("ClickableViewAccessibility")
    private void transitTypeSelector() {
        bike = findViewById(R.id.bike_button);
        drive = findViewById(R.id.car_button);
        walk = findViewById(R.id.walk_button);
        bus = findViewById(R.id.bus_button);

        walk.setText(R.string.walk_btn_text);
        bike.setText(R.string.bike_btn_text);
        drive.setText(R.string.drive_btn_text);
        bus.setText(R.string.bus_btn_text);


        bike.setOnTouchListener((v, event) -> {
            bike.setPressed(true);
            transportType = "b";
            drive.setPressed(false);
            bus.setPressed(false);
            walk.setPressed(false);
            Log.d("TYPE", transportType);
            return true;
        });

        drive.setOnTouchListener((v, event) -> {
            drive.setPressed(true);
            transportType = "d";
            bike.setPressed(false);
            bus.setPressed(false);
            walk.setPressed(false);
            Log.d("TYPE", transportType);
            return true;
        });

        bus.setOnTouchListener((v, event) -> {
            bus.setPressed(true);
            transportType = "r";
            drive.setPressed(false);
            bike.setPressed(false);
            walk.setPressed(false);
            Log.d("TYPE", transportType);
            return true;
        });

        walk.setOnTouchListener((v, event) -> {
            walk.setPressed(true);
            transportType = "w";
            drive.setPressed(false);
            bus.setPressed(false);
            bike.setPressed(false);
            Log.d("TYPE", transportType);
            return true;
        });
    }
}
