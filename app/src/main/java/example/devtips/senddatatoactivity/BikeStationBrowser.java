package example.devtips.senddatatoactivity;

import android.Manifest;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
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
import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.TravelMode;

import java.io.IOException;
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
    static String replace;
//    MarkerOptions origin, destination;
//    Polyline currentPolyline;

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
//        origin = new MarkerOptions().position(getLocationFromAddress(this, ChooseDestinationActivity.origin)).title("A");
//        destination = new MarkerOptions().position(getLocationFromAddress(this, ChooseDestinationActivity.destination)).title("B");
//
//        String url = getUrl(origin.getPosition(), destination.getPosition(), ChooseDestinationActivity.transportType);
//        new FetchURL(this).execute(url, ChooseDestinationActivity.transportType);
    }

    private String getUrl(LatLng origin, LatLng destination, String transportType) {
        String originStr = "origin=" + origin.latitude + "," + origin.longitude;
        String destStr = "destination=" + destination.latitude + "," + destination.longitude;
        String mode = "mode=" + transportType;

        String params = originStr + "&" + destStr + "&" + mode;
        String output = "json";

        return "https://maps.googelapis.com/maps/api/directions/" + output + "?" + params + "&key=" + getString(R.string.google_maps_key);
    }

    public LatLng getLocationFromAddress(Context context, String strAddress) {

        Geocoder coder = new Geocoder(context);
        List<Address> address;
        LatLng p1 = null;

        try {
            // May throw an IOException
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }

            Address location = address.get(0);
            p1 = new LatLng(location.getLatitude(), location.getLongitude());

        } catch (IOException ex) {

            ex.printStackTrace();
        }

        return p1;
    }
//

    private void queryDirections() {
        GeoApiContext context = new GeoApiContext().setApiKey("YOUR_API_KEY");
        String destination = ChooseDestinationActivity.destination;

        String origin = ChooseDestinationActivity.origin;
        LatLng originLatLng = getLocationFromAddress(this, origin);

        LatLng destLatLng = getLocationFromAddress(this, destination);

        DirectionsApiRequest apiRequest = DirectionsApi.newRequest(context);
        apiRequest.origin(new com.google.maps.model.LatLng(originLatLng.latitude, originLatLng.longitude));
        apiRequest.destination(new com.google.maps.model.LatLng(destLatLng.latitude, destLatLng.longitude));
        apiRequest.mode(TravelMode.DRIVING); //set travelling mode driving
//        switch (ChooseDestinationActivity.transportType) {
//            case "bicycling":
//                apiRequest.mode(TravelMode.BICYCLING); //set travelling mode
//                break;
//            case "driving":
//                apiRequest.mode(TravelMode.DRIVING); //set travelling mode driving
//                break;
//            case "walking":
//                apiRequest.mode(TravelMode.WALKING); //set travelling mode walking
//                break;
//            case "transit":
//                apiRequest.mode(TravelMode.TRANSIT); //set travelling mode transit
//                break;
//            case "default":
//                apiRequest.mode(TravelMode.UNKNOWN); //generic unknown travel mode
//
//        }

//        apiRequest.mode(TravelMode.DRIVING); //set travelling mode

        //noinspection unchecked
//TODO: Complete Directions API Call

//        apiRequest.setCallback(new Callback<DirectionsResult>() {
//            @Override
//            public void onResponse(Call<DirectionsResult> call, Response<DirectionsResult> response) {
//                DirectionsResult routes = response.body();
//                System.out.println("found " +routes.geocodedWaypoints.length  + " itineraries ot the dewstination");
//                System.out.println(routes);
//            }
//
//            @Override
//            public void onFailure(Call<DirectionsResult> call, Throwable t) {
//
//            }
//        });
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
//        mMap.setOnMarkerClickListener(marker -> {
//            String title = marker.getTitle();
//            replace = title.replace("Bike Station ", " ");
//            int bikeStationId = Integer.parseInt(replace.substring(0, 4)
//                    .replace(" ", ""));
//            Log.d("Bike station tapped", String.valueOf(bikeStationId)); //bikeStationId is the number parameter of each bikestation in JSON
//
//            return true;
//        });

// Implemented through the Maps Geolocation Framework
//        Location myLocation = mMap.getMyLocation();
//        if (myLocation != null) {
//            LatLng myLocationLatLng = new LatLng(myLocation.getLatitude(),
//                    myLocation.getLongitude());
//
//            mMap.addMarker(new MarkerOptions().position(myLocationLatLng)
//                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
//        }

    }

    public void addMarkerInMap(GoogleMap mMap, List<BikeStation> bikeStations) {
//        mMap.addMarker(destination);
//        mMap.addMarker(origin);
        for (BikeStation bikeStation : bikeStations) {
            Double lat = bikeStation.getPosition().getLatitude();
            Double lng = bikeStation.getPosition().getLongitude();
            LatLng bikeStationPosition = new LatLng(lat, lng);
            mMap.addMarker(new MarkerOptions().position(bikeStationPosition)
                    .title(bikeStation.getNumber()));

        }


    }

//    private void clusterMarkers() {
//        mClusterManager = new ClusterManager<>(this, mMap);
//        mMap.setOnCameraIdleListener(mClusterManager);
//        mMap.setOnMarkerClickListener(mClusterManager);
//        mMap.setOnInfoWindowClickListener(mClusterManager);
//        addMarkerInMap(mMap, respone);
//        mClusterManager.cluster();
//    }

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

//    @Override
//    public void onTaskDone(Object... values) {
//        if (currentPolyline != null)
//            currentPolyline.remove();
//        currentPolyline=mMap.addPolyline((PolylineOptions) values[0]);
//    }
}


