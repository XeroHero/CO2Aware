package example.devtips.senddatatoactivity;

import android.Manifest;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Objects;

import example.devtips.senddatatoactivity.models.BikeStation;
import example.devtips.senddatatoactivity.network.GetDataService;
import example.devtips.senddatatoactivity.network.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BikeStationBrowser extends FragmentActivity implements OnMapReadyCallback {
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    static GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                MY_PERMISSIONS_REQUEST_LOCATION);
        setContentView(R.layout.activity_journey_planner_itinerary);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng dublinGeneric = new LatLng(-53, 6);
        placeMarkers(dublinGeneric, mMap);
    }

    private void placeMarkers(LatLng dublinGeneric, GoogleMap mMap) {
        //addMarkers
        /*Create handle for the RetrofitInstance interface*/
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<BikeStation>> call = service.getAllBikeStations();
        call.enqueue(new Callback<List<BikeStation>>() {
            @Override
            public void onResponse(@NonNull Call<List<BikeStation>> call, @NonNull Response<List<BikeStation>> response) {
                addMarkerInMap(mMap, Objects.requireNonNull(response.body()));
            }

            @Override
            public void onFailure(@NonNull Call<List<BikeStation>> call, @NonNull Throwable t) {
                Toast.makeText(BikeStationBrowser.this.getApplicationContext(), "Error contacting API", Toast.LENGTH_SHORT).show();
            }
        });
        mMap.moveCamera(CameraUpdateFactory.newLatLng(dublinGeneric)); //set map to initially show a view of the entire city
        mMap.setMyLocationEnabled(true);
        defaultMapSettings(mMap);
    }

    public void addMarkerInMap(GoogleMap mMap, List<BikeStation> bikeStations) {
        for (BikeStation bikeStation : bikeStations) {
            Double lat = bikeStation.getPosition().getLatitude();
            Double lng = bikeStation.getPosition().getLongitude();
            LatLng bikeStationPosition = new LatLng(lat, lng);
            mMap.addMarker(new MarkerOptions().position(bikeStationPosition)
                    .title(bikeStation.getNumber()));
        }
    }

    public static void defaultMapSettings(GoogleMap googleMap) {
        googleMap.getUiSettings().setZoomControlsEnabled(false);
        googleMap.getUiSettings().setRotateGesturesEnabled(false);
        googleMap.getUiSettings().setMapToolbarEnabled(false);
        googleMap.getUiSettings().setTiltGesturesEnabled(true);
        googleMap.getUiSettings().setCompassEnabled(true);
        googleMap.getUiSettings().setIndoorLevelPickerEnabled(false);
        googleMap.setBuildingsEnabled(true);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(53.3498091, -6.2602548), 12.17F)); //center on Spire, zoom level 12.17F OK
    }
}

