package tk.xerohero.co2aware;


import android.content.Context;
import android.os.Build;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.ArrayList;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.Response;
import xdroid.toaster.Toaster;

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
    public interface OnReturnRecipeList {

        void onSuccess(ArrayList<BikeStation> value);
    }

    // Same as above.
    public interface OnFetchRecipeDetails {

        void onSuccess(Boolean result);
    }


    // Gets a single recipe as input -> Fetches all relevant information
    // regarding the recipe and stores it inside the object
    public void getRecipeInformation(BikeStation bikeStation, OnFetchRecipeDetails callback) {
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
//                        recipe.setIsVegan(element.get("vegan").getAsBoolean());
//                        recipe.setTimeToCook(element.get("readyInMinutes").getAsInt());
//                        if (!element.get("instructions").isJsonNull()) {
//                            recipe.setInstructions(element.get("instructions").getAsString());
//                        } else {
//                            recipe.setInstructions(
//                                    "Server does not have instructions for this recipe. Seems quite simple though, "
//                                            + "doesn't it? Look at the image, the ingredients and do it - just do it!");
//                        }
//                        recipe.setServings(element.get("servings").getAsInt());
//                        recipe.setCheap(element.get("cheap").getAsBoolean());
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

    public void fetchRecipes(LatLng userLocation, OnReturnRecipeList callback) {
        final ArrayList<BikeStation> bikeStations = new ArrayList<>();

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
                                bikeStations.add(buildRecipe(element.getAsJsonObject()));
                            });
                        }
                        callback.onSuccess(bikeStations);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    Toaster.toast("There has been an error fetching from the API");
                }

            }
        }).start();
    }


    private BikeStation buildRecipe(JsonObject element) {
        JsonObject returnObject = element.getAsJsonObject();
        String name = returnObject.get("title").getAsString();
        Integer id = Integer.parseInt(returnObject.get("id").toString());
        String image = returnObject.get("image").getAsString();

        // Fetch the ingredients that user didn't specify they had.
        JsonArray missingIngredientsArray = returnObject.get("missedIngredients").getAsJsonArray();
//        ArrayList<Ingredient> missingIngredients = new ArrayList<>();
//        for (JsonElement ingredient : missingIngredientsArray) {
//            missingIngredients.add(
//                    new Ingredient(ingredient.getAsJsonObject().get("name").getAsString(),
//                            ingredient.getAsJsonObject().get("aisle").getAsString(),
//                            ingredient.getAsJsonObject().get("image").getAsString()));
//        }
//
//        // Fetch the ingredients that user did specify and is used in the recipe.
//        JsonArray usedIngredientsArray = returnObject.get("usedIngredients").getAsJsonArray();
//        ArrayList<Ingredient> usedIngredients = new ArrayList<>();
//        for (JsonElement ingredient : usedIngredientsArray) {
//            usedIngredients.add(
//                    new Ingredient(ingredient.getAsJsonObject().get("name").getAsString(),
//                            ingredient.getAsJsonObject().get("aisle").getAsString(),
//                            ingredient.getAsJsonObject().get("image").getAsString()));
//        }

        String addressDisplay = "";

        LatLng latLngLocation = null;
        int bikesAvailable = 0, parkingPlacesAvailable=0;
        BikeStation bikeStation = new BikeStation(id, addressDisplay, latLngLocation, bikesAvailable, parkingPlacesAvailable);
//        recipe.setMissedIngredients(missingIngredients);
//        recipe.setUsedIngredients(usedIngredients);
//        return recipe;
    return bikeStation;
    }


}
