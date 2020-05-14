package example.devtips.senddatatoactivity.unused;

import android.util.Log;

import com.google.gson.Gson;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import example.devtips.senddatatoactivity.RetrofitArrayAPI;
import example.devtips.senddatatoactivity.models.BikeStation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APICaller {

    private static JSONObject jObj = null;
    private static String json = "";

    public String baseUrl = "https://api.jcdecaux.com/";
    public String suburl;

    public void getRetrofitArray() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitArrayAPI service = retrofit.create(RetrofitArrayAPI.class);
        Call<BikeStation> call = service.getJsonArrayData();

        call.enqueue(new Callback<BikeStation>() {
            @Override
            public void onResponse(Call<BikeStation> call, Response<BikeStation> response) {
                Log.e("response ", response.body().toString());
                Log.e("number ", response.body().getNumber().toString());
                Log.e("address ", response.body().getAddress().toString());
                Log.e("name ", response.body().getName().toString());

                Log.e("banking ", response.body().getBanking().toString());
                Log.e("bonus ", response.body().getBonus().toString());
                Log.e("bike_stands ", response.body().getBike_stands().toString());
                Log.e("available_bike_stands ", response.body().getAvailable_bike_stands().toString());
                Log.e("available_bikes ", response.body().getAvailable_bikes().toString());
                Log.e("status ", response.body().getStatus().toString());
                Log.e("last_update ", response.body().getLast_update().toString());
            }

            @Override
            public void onFailure(Call<BikeStation> call, Throwable t) {

                Log.e("Error: ", t.toString());
            }
        });
    }
    // constructor

    
/*
    static JSONObject getJSONFromUrl() {

        // Making HTTP request
        InputStream is = null;
        try {
            // defaultHttpClient
            DefaultHttpClient httpClient = new DefaultHttpClient();
            String url = "https://api.jcdecaux.com/vls/v1/stations?contract=dublin&apiKey=17566ea530bc36033158dda33346ffbc2313d405";
            HttpPost httpPost = new HttpPost(url);

            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            is = httpEntity.getContent();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            json = sb.toString();
            Gson gson = new Gson();
            Location location = gson.fromJson(json, Location.class);
            Log.d("PROVA","");
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }

        // try parse the string to a JSON object
        try {
            jObj = new JSONObject(json);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        // return JSON String
        return jObj;

    }*/
}