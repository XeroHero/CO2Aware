package example.devtips.senddatatoactivity;

import android.Manifest;
import android.location.Location;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.junit.Test;

import static example.devtips.senddatatoactivity.JourneyPlannerItinerary.*;
import static org.junit.Assert.assertThat;

public class JourneyPlannerItinerary extends FragmentActivity implements OnMapReadyCallback {
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    static GoogleMap mMap;

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
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker2).title("Bike Station 2 - Blessington Street"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker3).title("Bike Station 3 - Bolton Street"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker4).title("Bike Station 4 - "+"Greek Street"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker5).title("Bike Station 5 - "+"Charlemont Street"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker6).title("Bike Station 6 - Chirstchurch Place"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker7).title("Bike Station 7 - "+"High Street"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker8).title("Bike Station 8 - "+"Custom House Quay"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker9).title("Bike Station 9 - "+"Exchequer Street"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker10).title("Bike Station 10 - Dame Street"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker11).title("Bike Station 11 - " +"Earlsfort Terrace"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker12).title("Bike Station 12 - Eccles Street"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker13).title("Bike Station 13 - Fitzwilliam Square West"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker15).title("Bike Station 15 - Hardwicke Street"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker16).title("Bike Station 16 - " + "Georges Quay"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker17).title("Bike Station 17 - " + "Golden Lane"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker18).title("Bike Station 18 - Grantham Street"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker19).title("Bike Station 19 - "+"Herbert Place"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker21).title("Bike Station 21 - Leinster Street South"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker22).title("Bike Station 22 - Townsend Street"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker23).title("Bike Station 23 - Custom House"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker24).title("Bike Station 24 - Cathal Brugha Street"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker25).title("Bike Station 25 - "+"Merrion Square East"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker26).title("Bike Station  - Merrion Square West"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker27).title("Bike Station 27 - "+"Molesworth Street"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker28).title("Bike Station 28 - Mountjoy Square West"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker29).title("Bike Station 29 - Ormond Quay Upper"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker30).title("Bike Station 30 - Parnell Square North"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker31).title("Bike Station 81 - Parnell Street"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker32).title("Bike Station 32 - Pearse Street"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker33).title("Bike Station 33 - Princes Street / O'Connell Street"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker34).title("Bike Station 34 - Portobello Harbour"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker36).title("Bike Station 36 - St. Stephen's Green East"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker37).title("Bike Station 37 - "+"St. Stephen's Green South"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker38).title("Bike Station 38 - "+"Talbot Street"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker39).title("Bike Station 39 - Wilton Terrace"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker40).title("Bike Station 40 - Jervis Street"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker41).title("Bike Station 41 - Harcourt Terrace"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker42).title("Bike Station 42 - Smithfield North"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker43).title("Bike Station 43 - Portobello Road"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker44).title("Bike Station 44 - Upper Sherrard Street"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker45).title("Bike Station 45 - "+"Deverell Place"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker47).title("Bike Station 47 - "+"Herbert Street"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker48).title("Bike Station 48 - Excise Walk"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker49).title("Bike Station 49 - "+"Guild Street"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker50).title("Bike Station 50 - George's Lane"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker51).title("Bike Station 51 - "+"York Street West"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker52).title("Bike Station 52 - York Street East"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker53).title("Bike Station 53 - "+"Newman House"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker54).title("Bike Station 54 - Clonmel Street"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker55).title("Bike Station 55 - "+"Hatch Street"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker56).title("Bike Station 56 - Mount Street Lwr"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker57).title("Bike Station 57 - Grattan Street"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker58).title("Bike Station 58 - "+"Sir Patrick's Dun"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker59).title("Bike Station 59 - "+"Denmark Street Great"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker61).title("Bike Station 61 - " + "Hardwicke Place"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker62).title("Bike Station 62 - "+"Lime Street"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker63).title("Bike Station 63 - "+"Fenian Street"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker64).title("Bike Station 64 - Sandwith Street"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker65).title("Bike Station 65 - Convention Centre"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker66).title("Bike Station 66 - "+"New Central Bank"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker67).title("Bike Station 67 - "+"The Point"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker68).title("Bike Station 68 - "+"Hanover Quay"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker69).title("Bike Station 69 - "+"Grand Canal Dock"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker71).title("Bike Station 71 - "+"Kevin Street"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker72).title("Bike Station 72 - "+"John Street West"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker73).title("Bike Station 73 - "+"Francis Street"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker74).title("Bike Station 74 - " + "Oliver Bond Street"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker75).title("Bike Station 75 - James Street"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker76).title("Bike Station 76 - "+"Market Street South"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker77).title("Bike Station 77 - "+"Wolfe Tone Street"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker78).title("Bike Station 78 - Mater Hospital"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker79).title("Bike Station 79 - "+"Eccles Street East"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker80).title("Bike Station 80 - St James Hospital (Luas)"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker81).title("Bike Station 81 - St. James Hospital (Central)"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker82).title("Bike Station 82 - "+"Mount Brown"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker83).title("Bike Station 83 - Emmet Road"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker84).title("Bike Station 84 - " +"Brookfield Road"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker85).title("Bike Station 85 - Rothe Abbey"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker86).title("Bike Station 86 - Parkgate Street"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker87).title("Bike Station 87 - " + "Collins Barracks Museum"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker88).title("Bike Station 88 - Blackhall Place"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker89).title("Bike Station 89 - Fitzwilliam Square East"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker90).title("Bike Station 90 - " + "Benson Street"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker91).title("Bike Station 91 - "+"South Dock Road"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker92).title("Bike Station 92 - Heuston Bridge (North)"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker93).title("Bike Station  - Heuston Station (Central)"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker94).title("Bike Station 94 - "+"Heuston Station (Car Park)"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker95).title("Bike Station 95 - "+"Royal Hospital"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker96).title("Bike Station 96 - Kilmainham Lane"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker97).title("Bike Station 97 - "+"Kilmainham Gaol"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker98).title("Bike Station 98 - Frederick Street South"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker99).title("Bike Station 99 - "+"City Quay"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker100).title("Bike Station 100 - Heuston Bridge (South)"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker101).title("Bike Station 101 - "+"King Street North"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker102).title("Bike Station 102 - "+"Western Way"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker103).title("Bike Station 103 - Grangegorman Lower (South)"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker104).title("Bike Station 104 - "+"Grangegorman Lower (Central)"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker105).title("Bike Station 105 - Grangegorman Lower (North)"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker106).title("Bike Station 106 - " + "Rathdown Road"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker107).title("Bike Station 107 - Charleville Road"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker108).title("Bike Station 108 - Avondale Road"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker109).title("Bike Station 109 - Buckingham Street Lower"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker110).title("Bike Station 110 - Phibsborough Road").snippet("TEST!"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker111).title("Bike Station 111 - Mountjoy Square East"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker112).title("Bike Station 112 - " + "North Circular Road (O'Connell's)"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker113).title("Bike Station 113 - Merrion Square South"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker114).title("Bike Station 114 - "+"Wilton Terrace (Park)"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker115).title("Bike Station 115 - "+"Killarney Street" ));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker116).title("Bike Station 116 - "+"Broadstone"));
        mMap.addMarker(new MarkerOptions().position(BikeConstants.marker117).title("Bike Station 117 - "+"Hanover Quay East"));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(dublinGeneric)); //set map to initially show a view of the entire city

//        mMap.setMaxZoomPreference(10);
        mMap.setMyLocationEnabled(true);
        defaultMapSettings(mMap);
//        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
//            @Override
//            public boolean onMarkerClick(Marker marker) {
//                String title = marker.getTitle();
//                String replace = title.replace("Bike Station ", " ");
//                System.out.println("BIKE STN CLICKED: " + replace);
//                return true;
//            }
//        });
        Location myLocation = mMap.getMyLocation();
        if (myLocation != null) {
            LatLng myLocationLatLng = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());

            mMap.addMarker(new MarkerOptions().position(myLocationLatLng).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
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
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(53.3498091,-6.2602548), 12.17F)); //center on Spire, zoom level 12.17F OK
    }

}


