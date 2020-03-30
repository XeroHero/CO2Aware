package tk.xerohero.co2aware;


import android.content.Context;
import android.os.Build;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.Response;
import xdroid.toaster.Toaster;

import static java.lang.Integer.parseInt;

/**
 * Class to call the Spoonacular API, Fetch Results and Build Objects based n the results.
 */
public class APICaller {

    private Context context;
    //private static final String API_KEY = "d19babad42mshdc5aed524043719p108e29jsnb2e648b51c07";
    private static final String API_KEY = "17566ea530bc36033158dda33346ffbc2313d405";

//    private static final String API_HOST = "spoonacular-recipe-food-nutrition-v1.p.rapidapi.com";
//    private String apiKeyzz;

    public APICaller(Context context) {
        this.context = context;
    }

    // These two are used to make callbacks so that that the methods calling this class and its
    // fetchers are then able to work on the results without constantly polling to see if the result changed.
    public interface OnReturnBikeStationList {

        void onSuccess(ArrayList<BikeStation> value);
    }

    // Same as above.
    public interface OnFetchBikeStationDetails {

        void onSuccess(Boolean result);
    }


    // Gets a single recipe as input -> Fetches all relevant information
    // regarding the recipe and stores it inside the object
    public void getBikeStationInformation(BikeStation bikeStation, OnFetchBikeStationDetails callback) {
        int number = bikeStation.getBikeStationNumber();
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                Request request = new Builder()
                        .url("https://api.jcdecaux.com/vls/v1/stations?contract=dublin&apiKey=" + API_KEY)
                        .get()
                        .build();

                try {
                    Response response = client.newCall(request).execute();
                    JsonElement responseJson = new JsonParser().parse(response.body().string());

                    // If this check didn't pass, there's an issue - it should.
                    if (responseJson.isJsonObject()) {
                        JsonObject element = responseJson.getAsJsonObject();
                        bikeStation.setBikeStationNumber(element.get("number").getAsInt());
                        bikeStation.setAddress(element.get("address").getAsString());
                        //nested JSON is "position" node TODO: Check that this actually works
                        bikeStation.setLatitude(element.get("position/lat").getAsString());
                        bikeStation.setLongitude(element.get("position/lng").getAsString());
                        bikeStation.setBikesAvailable(element.get("available").getAsInt());

                        if (bikeStation.getBikesAvailable() == 0){
                            Log.d("BikeCountZero", "There are no bikes available at this location");
                        }
                        callback.onSuccess(true);


                    }else{
                        Log.d("Error", "Had issues parsing bike station info for Bike station  " + bikeStation.getBikeStationNumber());
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
    }

    /** Call on client side when rendering on UI
     *
     * @param userLocation LatLng object consturcted from user's location as obtained from GPS/other means
     * @param callback
     */

    public static final List<BikeStation> bikeStations = new ArrayList<>();

    public void fetchBikeStationData(LatLng userLocation, OnReturnBikeStationList callback) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();

                Request request = new Builder()
                        .url("https://api.jcdecaux.com/vls/v1/stations?contract=dublin&apiKey=" + API_KEY)
                        .get()
                        .build();
                try {
                    Response response = client.newCall(request).execute();
                    JsonElement responseJson = new JsonParser().parse(response.body().string());

                    if (responseJson.isJsonArray()) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            responseJson.getAsJsonArray().forEach((element) -> {
                                bikeStations.add(buildBikeStation(element.getAsJsonObject()));
                            });
                        }
                        callback.onSuccess((ArrayList<BikeStation>) bikeStations);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    Toaster.toast("There has been an error fetching from the API");
                }
            }
        }).start();
    }


    private BikeStation buildBikeStation(JsonObject element) {
        JsonObject returnObject = element.getAsJsonObject();
        BikeStation b;
        //are there bikes
        int bikesAvailable = returnObject.get("Bikes_available").getAsInt();
        int parkingPlacesAvailable = 0;

        Boolean pickupPossible = null;
        if (bikesAvailable > 0) {
            pickupPossible = true;
        }

        parkingPlacesAvailable = returnObject.get("parking_places").getAsInt();

        Boolean dropOffPossible = null;
        if (parkingPlacesAvailable > 0) {
            dropOffPossible = true;
        }

        Integer number = returnObject.get("Number").getAsInt();
        JsonElement lat = returnObject.get("lat");
        JsonElement lng = returnObject.get("lng");
//        LatLng latLngLocation = new LatLng(lat.getAsDouble(), lng.getAsDouble());

        String addressDisplay = "";

        if (dropOffPossible || pickupPossible) {
            b = new BikeStation(number, addressDisplay, lat.toString(), lng.toString(), bikesAvailable, parkingPlacesAvailable, dropOffPossible, pickupPossible);
        }

        else{ //return null object if there are no bikes available at the chosen location
            b = new BikeStation(null, null, null, null, null,null, dropOffPossible, pickupPossible);
        }
        return b;
    }
}
