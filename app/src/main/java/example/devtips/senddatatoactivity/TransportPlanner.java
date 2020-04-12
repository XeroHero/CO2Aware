package example.devtips.senddatatoactivity;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.maps.android.data.kml.KmlLayer;

public class TransportPlanner extends Activity {
    private GoogleMap googleMap; // Google map object.
    KmlLayer layer; //KML object.
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
//            layer = new KmlLayer(googleMap, R.locations.dbLocations,  getApplicationContext()); // creating the kml layer
            layer.addLayerToMap();// adding kml layer with the **google map**
        } catch (Exception e) {
            e.printStackTrace();
        }
    };



    //Class interacts with Bike Station ot get bike stations and directions to the destination (Maps API call to be made

    /*
    TODO: Add Maps API Key to correct file: AIzaSyBRg9JiQxX2hPCcTaWt7cjoWKDF5imZy5U (Maps SDK for Android API Key)

    TODO: Add Markers to Map for each of the Bike Stations


     */
}
