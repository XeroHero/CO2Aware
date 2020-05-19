package example.devtips.senddatatoactivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ChooseDestinationActivity extends AppCompatActivity {
    static String origin;
    static String destination;
    static String transportType;
    Button bus, walk, drive, bike;
//    Button bikeStations, searchGMaps;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_destination);
        //TODO ensure these two are filled
        EditText originTextbox = findViewById(R.id.origin_textbox);
        EditText destinationTextbox = findViewById(R.id.destination_textbox);
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
            transportType = "bicycling";
            drive.setPressed(false);
            bus.setPressed(false);
            walk.setPressed(false);
            Log.d("TYPE", transportType);
            return true;
        });

        drive.setOnTouchListener((v, event) -> {
            drive.setPressed(true);
            transportType = "driving";
            bike.setPressed(false);
            bus.setPressed(false);
            walk.setPressed(false);
            Log.d("TYPE", transportType);
            return true;
        });

        bus.setOnTouchListener((v, event) -> {
            bus.setPressed(true);
            transportType = "transit";
            drive.setPressed(false);
            bike.setPressed(false);
            walk.setPressed(false);
            Log.d("TYPE", transportType);
            return true;
        });

        walk.setOnTouchListener((v, event) -> {
            walk.setPressed(true);
            transportType = "walking";
            drive.setPressed(false);
            bus.setPressed(false);
            bike.setPressed(false);
            Log.d("TYPE", transportType);
            return true;
        });

        Button bikeStationBrowser = findViewById(R.id.search_button);
        bikeStationBrowser.setOnClickListener(v -> {
            startActivity(new Intent(ChooseDestinationActivity.this, BikeStationBrowser.class));
        });

        Button viewItineraries = findViewById(R.id.view_itineraries_button);
        viewItineraries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { String source = originTextbox.getText().toString();
                String destination = destinationTextbox.getText().toString();

                if (TextUtils.isEmpty(source)) {
                    originTextbox.setError("Enter Soruce point");
                } else if (TextUtils.isEmpty(destination)) {
                    destinationTextbox.setError("Enter Destination Point");
                } else {
                    String sendstring="http://maps.google.com/maps?saddr=" +
                            source +
                            "&daddr=" +
                            destination;
                    Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                            Uri.parse(sendstring));
                    startActivity(intent);
                }
            }

        });
    }
}
