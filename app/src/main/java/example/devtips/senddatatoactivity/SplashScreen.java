package example.devtips.senddatatoactivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SplashScreen extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_FOR_LOCATION = 1;
    Button journeyPlan;
    Button greenTips;
Button friendList;
    private Button about;

    //    NB: Required to request permissions in advance. Else, crash on first opening of GMaps
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == MY_PERMISSIONS_REQUEST_FOR_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] != PERMISSION_GRANTED) {
                boolean permissionGranted = false;
                do {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            MY_PERMISSIONS_REQUEST_FOR_LOCATION);
                    permissionGranted = grantResults[0] == PERMISSION_GRANTED;
                } while (!permissionGranted);

                Toast.makeText(this, "This app requires Location permission. Please allow it through Settings app or restart the app to receive the dialog again..", Toast.LENGTH_LONG).show();

            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_FOR_LOCATION);
        }

        setContentView(R.layout.activity_splash_screen);

        journeyPlan = findViewById(R.id.journey_planner_btn);
        journeyPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SplashScreen.this, ChooseDestinationActivity.class));
//                APICaller.getJSONFromUrl();
//                JSONFetch.jsonRequest();
            }
        });

        greenTips = findViewById(R.id.green_tips_btn);
        greenTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SplashScreen.this, TipsActivity.class));
            }
        });

        friendList =findViewById(R.id.friends_btn);
        friendList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SplashScreen.this, FriendsActivity.class));
            }
        });

        about = findViewById(R.id.about_btn);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SplashScreen.this, AboutActivity.class));
            }
        });
    }

    String url = "https://api.jcdecaux.com/vls/v1/stations?contract=dublin&apiKey=17566ea530bc36033158dda33346ffbc2313d405";
    ArrayList<BikeStation> bikeStationList = new ArrayList<>();

    private void getData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        BikeStation bikeStation = new BikeStation();
                        bikeStation.setName(jsonObject.getString("name"));
                        bikeStation.setAddress(jsonObject.getString("address"));
                        bikeStation.setNumber(jsonObject.getInt("number"));
                        bikeStation.setLat(jsonObject.getDouble("lat"));
                        bikeStation.setLng(jsonObject.getDouble("lng"));
                        bikeStation.setBanking(jsonObject.getBoolean("banking"));
                        bikeStation.setBonus(jsonObject.getBoolean("bonus"));
                        bikeStation.setParkingAvailable(jsonObject.getInt("available_bike_stands"));
                        bikeStation.setBikesAvailable(jsonObject.getInt("available_bikes"));
                        bikeStation.setStatus(jsonObject.getString("status"));
                        bikeStation.setLastUpdate(jsonObject.getLong("last_update"));

                        bikeStationList.add(bikeStation);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        progressDialog.dismiss();
                    }
                }
//                adapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());
                progressDialog.dismiss();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }
}
