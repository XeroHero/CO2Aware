package example.devtips.senddatatoactivity;

import android.app.Activity;
import android.os.Bundle;

import com.google.maps.android.data.kml.KmlLayer;

public class TransportPlanner extends Activity {
    KmlLayer layer; //KML object.

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            layer.addLayerToMap();// adding kml layer with the **google map**
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
