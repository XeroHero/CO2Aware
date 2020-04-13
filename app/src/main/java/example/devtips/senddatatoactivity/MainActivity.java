package example.devtips.senddatatoactivity;

import android.app.ProgressDialog;
import android.content.Intent;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText etMessage;
    private Button btnSendData;
    private RecyclerView mList;

    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private List<BikeStation> bikeStationList;
    private RecyclerView.Adapter adapter;
    private String url = "https://api.jcdecaux.com/vls/v1/stations?contract=dublin&apiKey=17566ea530bc36033158dda33346ffbc2313d405";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mList = findViewById(R.id.main_list);

        bikeStationList = new ArrayList<>();
        adapter = new BikeStationAdapter(getApplicationContext(),bikeStationList);

        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dividerItemDecoration = new DividerItemDecoration(mList.getContext(), linearLayoutManager.getOrientation());

        mList.setHasFixedSize(true);
        mList.setLayoutManager(linearLayoutManager);
        mList.addItemDecoration(dividerItemDecoration);
        mList.setAdapter(adapter);
        etMessage = (EditText) findViewById(R.id.et_message);
        btnSendData = (Button) findViewById(R.id.btn_send_data);

        btnSendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!TextUtils.isEmpty(etMessage.getText().toString().trim())){

                    Intent intent = new Intent(MainActivity.this, TipsActivity.class);
                    intent.putExtra("message", etMessage.getText().toString().trim());
                    startActivity(intent);

                } else {

                    Toast.makeText(MainActivity.this, R.string.fill_with_message, Toast.LENGTH_SHORT).show();

                }

            }
        });

        getData();

    }

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
                adapter.notifyDataSetChanged();
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
}
