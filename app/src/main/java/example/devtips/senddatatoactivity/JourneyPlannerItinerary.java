package example.devtips.senddatatoactivity;

import android.Manifest;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class JourneyPlannerItinerary extends FragmentActivity implements OnMapReadyCallback {
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                MY_PERMISSIONS_REQUEST_LOCATION);
        setContentView(R.layout.activity_journey_planner_itinerary);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
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
//        mMap.addMarker(new MarkerOptions().position(marker41).title("Dublin Bike Station 41 - Smithfield North"));


//        mMap.addMarker(new MarkerOptions().position(marker30).title("Dublin Bike Station 30 - Parnell Sq. North"));
        // Add a marker in Sydney and move the camera
        LatLng dublinGeneric = new LatLng(-53, 6);


        placeMarkers(dublinGeneric, mMap);
    }

    private static void placeMarkers(LatLng dublinGeneric, GoogleMap mMap) {
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker2).title("Bike Station 2"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker3).title("Bike Station 3"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker4).title("Bike Station 4"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker5).title("Bike Station 5"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker6).title("Bike Station 6"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker7).title("Bike Station 7"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker8).title("Bike Station 8"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker9).title("Bike Station 9"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker10).title("Bike Station 10"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker11).title("Bike Station 11"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker12).title("Bike Station 12"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker13).title("Bike Station 13"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker15).title("Bike Station 15"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker16).title("Bike Station 16"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker17).title("Bike Station 17"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker18).title("Bike Station 18"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker19).title("Bike Station 19"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker21).title("Bike Station 21"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker22).title("Bike Station 22"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker23).title("Bike Station 23"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker24).title("Bike Station 24"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker25).title("Bike Station 25"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker26).title("Bike Station 26"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker27).title("Bike Station 27"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker28).title("Bike Station 28"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker29).title("Bike Station 29"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker30).title("Bike Station 30"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker31).title("Bike Station 31"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker32).title("Bike Station 32"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker33).title("Bike Station 33"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker34).title("Bike Station 34"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker36).title("Bike Station 36"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker37).title("Bike Station 37"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker38).title("Bike Station 38"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker39).title("Bike Station 39"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker40).title("Bike Station 40"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker41).title("Bike Station 41"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker42).title("Bike Station 42"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker43).title("Bike Station 43"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker44).title("Bike Station 44"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker45).title("Bike Station 45"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker47).title("Bike Station 47"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker48).title("Bike Station 48"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker49).title("Bike Station 49"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker50).title("Bike Station 50"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker51).title("Bike Station 51"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker52).title("Bike Station 52"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker53).title("Bike Station 53"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker54).title("Bike Station 54"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker55).title("Bike Station 55"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker56).title("Bike Station 56"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker57).title("Bike Station 57"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker58).title("Bike Station 58"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker59).title("Bike Station 59"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker61).title("Bike Station 61"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker62).title("Bike Station 62"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker63).title("Bike Station 63"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker64).title("Bike Station 64"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker65).title("Bike Station 65"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker66).title("Bike Station 66"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker67).title("Bike Station 67"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker68).title("Bike Station 68"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker69).title("Bike Station 69"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker71).title("Bike Station 71"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker72).title("Bike Station 72"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker73).title("Bike Station 73"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker74).title("Bike Station 74"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker75).title("Bike Station 75"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker76).title("Bike Station 76"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker77).title("Bike Station 77"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker78).title("Bike Station 78"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker79).title("Bike Station 79"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker80).title("Bike Station 80"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker81).title("Bike Station 81"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker82).title("Bike Station 82"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker83).title("Bike Station 83"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker84).title("Bike Station 84"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker85).title("Bike Station 85"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker86).title("Bike Station 86"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker87).title("Bike Station 87"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker88).title("Bike Station 88"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker89).title("Bike Station 89"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker90).title("Bike Station 90"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker91).title("Bike Station 91"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker92).title("Bike Station 92"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker93).title("Bike Station 93"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker94).title("Bike Station 94"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker95).title("Bike Station 95"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker96).title("Bike Station 96"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker97).title("Bike Station 97"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker98).title("Bike Station 98"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker99).title("Bike Station 99"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker100).title("Bike Station 100"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker101).title("Bike Station 101"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker102).title("Bike Station 102"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker103).title("Bike Station 103"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker104).title("Bike Station 104"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker105).title("Bike Station 105"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker106).title("Bike Station 106"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker107).title("Bike Station 107"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker108).title("Bike Station 108"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker109).title("Bike Station 109"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker110).title("Bike Station 110"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker111).title("Bike Station 111"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker112).title("Bike Station 112"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker113).title("Bike Station 113"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker114).title("Bike Station 114"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker115).title("Bike Station 115"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker116).title("Bike Station 116"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker117).title("Bike Station 117"));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(dublinGeneric)); //set map to initially show a view of the entire city

//        mMap.setMaxZoomPreference(10);
        mMap.setMyLocationEnabled(true);

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                String title = marker.getTitle();
                String replace = title.replace("Bike Station ", " ");
                System.out.println("BIKE STN CLICKED: "+ replace);
                return true;
            }
        });
//        mMap.setMinZoomPreference();
    }

    private static String cropTitle(String title) {

        title.replace("Bike Station ", "");
        return title;
    }
}
