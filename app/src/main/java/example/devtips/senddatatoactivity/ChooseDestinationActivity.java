package example.devtips.senddatatoactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ChooseDestinationActivity extends AppCompatActivity {
    static String origin;
    static String destination;
    static String transportType;
    Button bus, walk, drive, bike;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_destination);

        EditText originTextbox = findViewById(R.id.origin_textbox);
        EditText destinationTextbox = findViewById(R.id.destination_textbox);
        bike = findViewById(R.id.bike_button);
        drive = findViewById(R.id.car_button);
        walk = findViewById(R.id.walk_button);
        bus = findViewById(R.id.bus_button);

        walk.setText("Walk!");
        bike.setText("Cycle!");
        drive.setText("Drive!");
        bus.setText("Bus!");

        bike.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                bike.setPressed(true);
                transportType = "bicycling";
                drive.setPressed(false);
                bus.setPressed(false);
                walk.setPressed(false);
                Log.d("TYPE", transportType);
                return true;
            }
        });

        drive.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                drive.setPressed(true);
                transportType = "driving";
                bike.setPressed(false);
                bus.setPressed(false);
                walk.setPressed(false);
                Log.d("TYPE", transportType);
                return true;
            }
        });

        bus.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                bus.setPressed(true);
                transportType = "transit";
                drive.setPressed(false);
                bike.setPressed(false);
                walk.setPressed(false);
                Log.d("TYPE", transportType);
                return true;
            }
        });

        walk.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                walk.setPressed(true);
                transportType = "walking";
                drive.setPressed(false);
                bus.setPressed(false);
                bike.setPressed(false);
                Log.d("TYPE", transportType);
                return true;
            }
        });



        Button searchItineraryDatabase = findViewById(R.id.search_button);

        searchItineraryDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destination = destinationTextbox.getText().toString();
                origin = originTextbox.getText().toString();


                Log.d("TYPE", transportType);

                Log.d("ORIG", origin);
                Log.d("DEST", destination);
                startActivity(new Intent(ChooseDestinationActivity.this, JourneyPlannerItinerary.class));
            }
        });
    }
}
